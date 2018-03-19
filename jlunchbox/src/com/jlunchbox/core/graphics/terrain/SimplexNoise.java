package com.jlunchbox.core.graphics.terrain;

import java.util.Random;

import org.joml.Vector2f;

public class SimplexNoise {
	
	private final Random rnd;
	private final int seed;

	private static int grad3[][] = {{1,1,0},{-1,1,0},{1,-1,0},{-1,-1,0},
              {1,0,1},{-1,0,1},{1,0,-1},{-1,0,-1},
              {0,1,1},{0,-1,1},{0,1,-1},{0,-1,-1}};

	private int[] table;
	
	private static final float F2 = (float) (0.5*(Math.sqrt(3.0) - 1.0));
	private static final float G2 = (float) ((3.0 - Math.sqrt(3.0))/6.0);
	private static final int NUM = 5;
	
	private final int octaves;
	private final float persistence;
	private final float lacunarity;
	private final Vector2f offset;
	private Vector2f[] octavesOffset;
	
	

	public SimplexNoise(int seed, int octaves, float persistence, float lacunarity, Vector2f offset) {
		this.seed = seed;
		this.octaves = octaves;
		this.persistence = persistence;
		this.lacunarity = lacunarity;
		this.offset = offset;
		rnd = new Random(seed);
		randomize();
	}

	private void randomize() {
		table = new int[512];
		for (int i = 0; i < 256; i++) {
			table[i] = i;
		}
		for (int n = 0; n < NUM; n++) {
			for (int i = 0; i < 256; i++) {
				int change = i + rnd.nextInt(256 - i);
				int temp = table[i];
				table[i] = table[change];
				table[change] = temp;
			}
		}
		for (int i = 256; i < 512; i++) {
			table[i] = table[i&255];
		}
		octavesOffset = new Vector2f[octaves];
		for (int i = 0; i < octaves; i++) {
			float x = (rnd.nextFloat() * 2 - 1) * 10000;
			float y = (rnd.nextFloat() * 2 - 1) * 10000;
			octavesOffset[i] = new Vector2f(x, y);
		}
	}
	
	private static int fastfloor(float x) {
		int xi = (int) x;
		return x < xi ? xi - 1 : xi;
	}
	
	private static float dot(int g[], float x, float y) {
		return g[0] * x + g[1] * y;
	}
	
	public float noise(float xin, float yin) {

		float n0, n1, n2;

		// Skew the input space to determine which simplex cell we're in
		float s = (xin + yin)*F2;
		int i = fastfloor(xin + s);
		int j = fastfloor(yin + s);
		
		// Compute distances from cell origin;
		float t = (i + j)*G2;
		float x0 = xin - i + t;
		float y0 = yin - j + t;
		
		// Determine which triangle we are in
		int i1, j1;
		if (x0 > y0) { i1 = 1; j1 = 0; }
		else { i1 = 0; j1 = 1; }
		
		// Offsets for middle corner and last corner
		float x1 = x0 - i1 + G2;
		float y1 = y0 - j1 + G2;
		float x2 = x0 - 1.0f + 2.0f*G2;
		float y2 = y0 - 1.0f + 2.0f*G2;
		
		// Work out the hashed gradient indices of the three simplex corners
		int ii = i & 255;
		int jj = j & 255;
		int gi0 = table[ii + table[jj]] % 12;
		int gi1 = table[ii + i1 + table[jj + j1]] % 12;
		int gi2 = table[ii + 1 + table[jj + 1]] % 12;
		
		// Calculate contribution from the three corners
		float t0 = 0.5f - x0*x0 - y0*y0;
		if (t0 < 0f) n0 = 0f; 
		else {
			t0 *= t0;
			n0 = t0 * t0 * dot(grad3[gi0], x0, y0);
		}
		float t1 = 0.5f - x1*x1 - y1*y1;
		if (t1 < 0f) n1 = 0f; 
		else {
			t1 *= t1;
			n1 = t1 * t1 * dot(grad3[gi1], x1, y1);
		}
		float t2 = 0.5f - x2*x2 - y2*y2;
		if (t2 < 0f) n2 = 0f; 
		else {
			t2 *= t2;
			n2 = t2 * t2 * dot(grad3[gi2], x2, y2);
		}
		
		// Combine
		return 70.0f*(n0 + n1 + n2);
	}
	
	public float getNoise(float x, float y) {
		
		float total = 0f;
		float amplitude = 1f;
		float frequency = 1f;

		for (int i = 0; i < octaves; i++) {
			
			float sx = x * frequency;
			float sy = y * frequency;

			float value = noise(sx, sy);
			total += value * amplitude;
			
			amplitude *= persistence;
			frequency *= lacunarity;
		}
				
	}
	
	
	
	public int[] getTable() { return table; }
	
	

}
