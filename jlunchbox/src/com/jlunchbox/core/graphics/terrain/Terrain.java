package com.jlunchbox.core.graphics.terrain;

import java.util.HashMap;
import java.util.Random;

import org.joml.Vector2i;

public class Terrain {
	
	private final long seed;
	private final TerrainSettings settings;
	private final ChunkGenerator generator;
	
	private final HashMap<Vector2i, Chunk> loadedChunks;
	
	public Terrain() { this(new Random().nextLong()); } 
	
	public Terrain(long seed) { 
		this.seed = seed;
		settings = new TerrainSettings().setDefaults();
		generator = new ChunkGenerator(this);
		loadedChunks = new HashMap<Vector2i, Chunk>();
	}
	
	public Chunk hasChunk(Vector2i pos) {
		Chunk out = loadedChunks.get(pos);
		if (out == null) {
			out = generator.generate(pos.x, pos.y);
			loadedChunks.put(pos, out);
		}
		return out;
	}
	
	public Chunk hasChunk(int x, int z) { return hasChunk(new Vector2i(x, z)); }

	public long getSeed() { return seed; }
	public TerrainSettings getSettings() { return settings; }
	
	// For testing
	public ChunkGenerator getGenerator() { return generator; }

}
