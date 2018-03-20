package com.jlunchbox.core.graphics.terrain;

public class TerrainGenerator {
	
	SimplexNoise2 noiseGen;
	TerrainSettings settings;
	TerrainChunk testChunk;

	public TerrainGenerator() {
		noiseGen = new SimplexNoise2(42);
		settings = new TerrainSettings().setDefaults();
		testChunk = new TerrainChunk(64, 64);
	}
	
	

}
