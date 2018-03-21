package com.jlunchbox.core.graphics.terrain;

import java.util.Random;

public class PerlinNoise {
	
	private final int[] p;
	private final float xCoord, yCoord, zCoord;

	public PerlinNoise() { this(new Random().nextLong()); }

	public PerlinNoise(long seed) { this(new Random(seed)); }
	
	public PerlinNoise(Random rng) {
		
		// Create the permutation table
		p = new int[512];
		for (int i = 0; i < 256; i++) p[i] = i;
		// Shuffle the numbers in the table
		for (int n = 0; n < 5; n++) {
			for (int i = 0; i < 256; i++) {
				int from = i + rng.nextInt(256 - i);
				int temp = p[i];
				p[i] = p[from];
				p[from] = temp;
			}
		}
		for (int i = 0; i < 256; i++) p[i + 256] = p[i];

		// Create coordinate offsets
		xCoord = rng.nextFloat() * 256f;
		yCoord = rng.nextFloat() * 256f;
		zCoord = rng.nextFloat() * 256f;
		
	}
	
	// NOTE : Could back the noise function into this function to make this
	// function slightly faster!
	public void populateNoiseArray(float[] anoise, 
			float xOffset, float yOffset, float zOffset, 
			int xSize, int ySize, int zSize, 
			float xScale, float yScale, float zScale, float noiseScale) {

		if (ySize == 1) { // 2D noise
			
			float amp = 1.0f/noiseScale;

			int index = 0;
			for (int i = 0; i < xSize; i++) {
				float u = xOffset + (float)i * xScale + xCoord;
				for (int j = 0; j < zSize; j++) {
					float w = zOffset + (float)j * zScale + zCoord;
					anoise[index++] += noise(u, w) * amp;
				}
			}
				
		}
		
	}
	
	public float noise(float x, float y) {
		
	   	int xi = fastfloor(x) & 255;
    	int yi = fastfloor(y) & 255;
    	int g1 = p[p[xi] + yi];
    	int g2 = p[p[xi + 1] + yi];
    	int g3 = p[p[xi] + yi + 1];
    	int g4 = p[p[xi + 1] + yi + 1];
    	
    	float xf = x - fastfloor(x);
    	float yf = y - fastfloor(y);
    	
    	float d1 = grad(g1, xf, yf);
    	float d2 = grad(g2, xf - 1, yf);
    	float d3 = grad(g3, xf, yf - 1);
    	float d4 = grad(g4, xf - 1, yf - 1);
    	
    	float u = fade(xf);
    	float v = fade(yf);
    	
    	return lerp(v, lerp(u, d1, d2), lerp(u, d3, d4));
    	
	}

	public float noise(float x, float y, float z) {
		int X = fastfloor(x) & 255,                  	// FIND UNIT CUBE THAT
		    Y = fastfloor(y) & 255,                  	// CONTAINS POINT.
		    Z = fastfloor(z) & 255;
		x -= fastfloor(x);                           	// FIND RELATIVE X,Y,Z
		y -= fastfloor(y);                           	// OF POINT IN CUBE.
		z -= fastfloor(z);
		float u = fade(x),                           	// COMPUTE FADE CURVES
	          v = fade(y),                           	// FOR EACH OF X,Y,Z.
	          w = fade(z);
	    int A = p[X  ]+Y, AA = p[A]+Z, AB = p[A+1]+Z,   // HASH COORDINATES OF
	        B = p[X+1]+Y, BA = p[B]+Z, BB = p[B+1]+Z;   // THE 8 CUBE CORNERS,

	    return lerp(w, lerp(v, lerp(u, grad(p[AA  ], x  , y  , z   ),  // AND ADD
	                                   grad(p[BA  ], x-1, y  , z   )), // BLENDED
	                           lerp(u, grad(p[AB  ], x  , y-1, z   ),  // RESULTS
	                                   grad(p[BB  ], x-1, y-1, z   ))),// FROM  8
	                   lerp(v, lerp(u, grad(p[AA+1], x  , y  , z-1 ),  // CORNERS
	                                   grad(p[BA+1], x-1, y  , z-1 )), // OF CUBE
	                           lerp(u, grad(p[AB+1], x  , y-1, z-1 ),
	                                   grad(p[BB+1], x-1, y-1, z-1 ))));
	   }

	static int fastfloor(float x) {
		int xi = (int) x;
		return x < xi ? xi - 1 : xi;
	}

	static float fade(float t) { return t * t *t *( t * (t * 6 - 15) + 10); }
	
	static float lerp(float t, float a, float b) { return a + t * (b - a); }
	
    static float grad(int hash, float x, float y) {
    	switch(hash & 3)
    	{
	    	case 0: return  x + y;
	    	case 1: return -x + y;
	    	case 2: return  x - y;
	    	case 3: return -x - y;
	    	default: return 0f;
    	}
    }
    
	// Source: http://riven8192.blogspot.com/2010/08/calculate-perlinnoise-twice-as-fast.html
    static float grad(int hash, float x, float y, float z)
    {
        switch(hash & 0xF)
        {
            case 0x0: return  x + y;
            case 0x1: return -x + y;
            case 0x2: return  x - y;
            case 0x3: return -x - y;
            case 0x4: return  x + z;
            case 0x5: return -x + z;
            case 0x6: return  x - z;
            case 0x7: return -x - z;
            case 0x8: return  y + z;
            case 0x9: return -y + z;
            case 0xA: return  y - z;
            case 0xB: return -y - z;
            case 0xC: return  y + x;
            case 0xD: return -y + z;
            case 0xE: return  y - x;
            case 0xF: return -y - z;
            default: return 0; // never happens
        }
    }
}
