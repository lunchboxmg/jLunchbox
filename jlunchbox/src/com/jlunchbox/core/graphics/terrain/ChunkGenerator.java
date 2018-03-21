package com.jlunchbox.core.graphics.terrain;

import java.util.Random;

public class ChunkGenerator {
	
	private final Terrain terrain;
	private final Random rng;
	private final NoiseGeneratorOctaves noiseGen;

	public ChunkGenerator(Terrain terrain) {
		this.terrain = terrain;
		rng = new Random(terrain.getSeed());
		noiseGen = new NoiseGeneratorOctaves(rng, 16);
	}
	
	public Chunk generate(int xChunk, int zChunk) {

		Chunk chunk = new Chunk(terrain, xChunk, zChunk);
		
		return chunk.fromData(noiseGen.generateNoise(xChunk, zChunk, 16, 200.0f, 200.0f));
	}

}
