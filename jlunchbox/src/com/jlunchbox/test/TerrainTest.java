package com.jlunchbox.test;

import com.jlunchbox.core.graphics.terrain.TerrainGenerator;

public class TerrainTest {

	private TerrainGenerator generator;
	
	public TerrainTest() {
		
		generator = new TerrainGenerator();
	
	}
	
	public void test() {
		
		System.out.println("Testing terrain generation.");
		
	}
	
	public TerrainGenerator getGenerator() {return generator;}

}
