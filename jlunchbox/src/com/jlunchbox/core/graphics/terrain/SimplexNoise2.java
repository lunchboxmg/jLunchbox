package com.jlunchbox.core.graphics.terrain;

/**
 * A speed-improved simplex noise algorithm for 2D in Java.
 *
 * Based on example code by Stefan Gustavson (stegu@itn.liu.se).
 * Optimisations by Peter Eastman (peastman@drizzle.stanford.edu).
 * Better rank ordering method by Stefan Gustavson in 2012.
 *
 * This could be speeded up even further, but it's useful as it is.
 *
 * Version 2012-03-09
 *
 * This code was placed in the public domain by its original author,
 * Stefan Gustavson. You may use it as you see fit, but
 * attribution is appreciated.
 *
 * @author lunchboxmg
 * - Added a means of randomizing the permutation table
 */
import java.util.Random;

public class SimplexNoise2 extends NoiseGenerator{

    private static Grad[] grad2 = {
            new Grad(1, 1), new Grad(-1, 1), new Grad(1, -1), new Grad(-1, -1),
            new Grad(1, 0), new Grad(-1, 0), new Grad(1, 0), new Grad(-1, 0),
            new Grad(0, 1), new Grad(0, -1), new Grad(0, 1), new Grad(0, -1)
        };

    // Skewing and unskewing factors for 2, 3, and 4 dimensions
    private static final float F2 = (float) (0.5 * (Math.sqrt(3.0) - 1.0));
    private static final float G2 = (float) ((3.0 - Math.sqrt(3.0)) / 6.0);

    // Keep so we can put in a save file later
	private final int seed;
	
	// Permutation table
	private final int[] perm;
	private final int[] permMod12;
	

	public SimplexNoise2() { this(new Random().nextInt(Integer.MAX_VALUE/2)); }
	
	public SimplexNoise2(int seed) {

		this.seed = seed;
		Random rng = new Random(seed);

		// Initialize the permutation table
		this.perm = new int[512];
		for (int i = 0; i < 256; perm[i] = i++) { ; };

		// Shuffle the numbers in the table
		for (int n = 0; n < 5; n++) {
			for (int i = 0; i < 256; i++) {
				int from = i + rng.nextInt(256 - i);
				int temp = perm[i];
				perm[i] = perm[from];
				perm[from] = temp;
			}
		}

		// Duplicate back half
		for (int i = 0; i < 256; i++) {
			perm[256 + i] = perm[i];
		}
		
		this.permMod12 = new int[512];
		for (int i = 0; i < 512; i++) {
			permMod12[i] = perm[i] % 12;
		}
	}
	
	private static int fastfloor(float x) {
		int xi = (int) x;
		return x < xi ? xi - 1 : xi;
	}
	
    private static float dot(Grad g, float x, float y) {
        return g.x * x + g.y * y;
    }

    // 2D simplex noise
    public float getNoise(float xin, float yin) {
        
    	float n0, n1, n2; // Noise contributions from the three corners
        // Skew the input space to determine which simplex cell we're in
    	float s = (xin + yin) * F2; // Hairy factor for 2D
        int i = fastfloor(xin + s);
        int j = fastfloor(yin + s);
        float t = (i + j) * G2;
        float originX0 = i - t; // Unskew the cell origin back to (x,y) space
        float originY0 = j - t;
        float x0 = xin - originX0; // The x,y distances from the cell origin
        float y0 = yin - originY0;
        // For the 2D case, the simplex shape is an equilateral triangle.
        // Determine which simplex we are in.
        int i1, j1; // Offsets for second (middle) corner of simplex in (i,j) coords
        if (x0 > y0) {
            i1 = 1;
            j1 = 0;
        } else {
            // lower triangle, XY order: (0,0)->(1,0)->(1,1)
            i1 = 0;
            j1 = 1;
        } // upper triangle, YX order: (0,0)->(0,1)->(1,1)
        // A step of (1,0) in (i,j) means a step of (1-c,-c) in (x,y), and
        // a step of (0,1) in (i,j) means a step of (-c,1-c) in (x,y), where
        // c = (3-sqrt(3))/6
        float x1 = x0 - i1 + G2; // Offsets for middle corner in (x,y) unskewed coords
        float y1 = y0 - j1 + G2;
        float x2 = x0 - 1.0f + 2.0f * G2; // Offsets for last corner in (x,y) unskewed coords
        float y2 = y0 - 1.0f + 2.0f * G2;
        // Work out the hashed gradient indices of the three simplex corners
        int ii = i & 255;
        int jj = j & 255;
        int gi0 = permMod12[ii + perm[jj]];
        int gi1 = permMod12[ii + i1 + perm[jj + j1]];
        int gi2 = permMod12[ii + 1 + perm[jj + 1]];
        // Calculate the contribution from the three corners
        float t0 = 0.5f - x0 * x0 - y0 * y0;
        if (t0 < 0) {
            n0 = 0.0f;
        } else {
            t0 *= t0;
            n0 = t0 * t0 * dot(grad2[gi0], x0, y0); // (x,y) of grad3 used for 2D gradient
        }
        float t1 = 0.5f - x1 * x1 - y1 * y1;
        if (t1 < 0) {
            n1 = 0.0f;
        } else {
            t1 *= t1;
            n1 = t1 * t1 * dot(grad2[gi1], x1, y1);
        }
        float t2 = 0.5f - x2 * x2 - y2 * y2;
        if (t2 < 0) {
            n2 = 0.0f;
        } else {
            t2 *= t2;
            n2 = t2 * t2 * dot(grad2[gi2], x2, y2);
        }
        // Add contributions from each corner to get the final noise value.
        // The result is scaled to return values in the interval [-1,1].
        return 70.0f * (n0 + n1 + n2);
    }

    // Inner class to speed up gradient computations
    // (array access is a lot slower than member access)
    private static class Grad {
        float x, y;
        Grad(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int getSeed() { return seed; }

    public void populateNoiseArray()
    
}
