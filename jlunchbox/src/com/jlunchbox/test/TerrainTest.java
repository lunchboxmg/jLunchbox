package com.jlunchbox.test;

import java.util.Arrays;

import com.jlunchbox.core.graphics.terrain.Chunk;
import com.jlunchbox.core.graphics.terrain.Terrain;

public class TerrainTest {

	private Terrain terrain;
	
	public TerrainTest() {
		
		terrain = new Terrain(42337);
	
	}
	
	public void test() {
		
		System.out.println("Testing terrain generation.");
		
		Chunk test = terrain.hasChunk(0, 0);
		
		System.out.println(Arrays.toString(test.getData()));
		
		test.genMesh();
		
	}
	

}
