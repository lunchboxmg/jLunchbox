package com.jlunchbox.core.graphics.terrain;

import java.util.Random;

public class NoiseGeneratorOctaves {
	
	private final PerlinNoise[] generators;
	private final int octaves;

	public NoiseGeneratorOctaves(Random rng, int octaves) {
		
		this.octaves = octaves;
		this.generators = new PerlinNoise[octaves];
		
		// Initialize each other Perlin Noise based off the same random number
		// generator so that the noise can be replicated and each octaves has
		// a permutation table that is separate from others.
		for (int i = 0; i < octaves; i++)
			this.generators[i] = new PerlinNoise(rng);

	}
	
	public float[] generateNoise(int chunkX, int chunkZ, int length, float xScale, float zScale) {
		
		float [] anoise = new float[length * length];
		
		float d3 = 1.0f;
		float maxAmp = 0.0f;
		
		for (int i = 0; i < octaves; i++) 
		{
			float u = (float)chunkX * d3 * xScale;
			float w = (float)chunkZ * d3 * zScale;
			
			
			
			
			
			generators[i].populateNoiseArray(anoise, u, 10, w, length, 1, length, xScale * d3, 1.0f, zScale * d3, d3);
			
			maxAmp += 1.0f / d3;
			d3 *= 0.5f;
		}
		
		System.out.println("Maximum Amplitude: " + maxAmp);
		return anoise;
	}
	
	static long fastfloor(float x) {
		long xl = (long) x;
		return x < xl ? xl - 1 : xl;
	}
}
