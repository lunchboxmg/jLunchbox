package com.jlunchbox.test;

import java.util.Arrays;

import com.jlunchbox.core.graphics.terrain.SimplexNoise;

public class TerrainTest {
	
	SimplexNoise sng;

	public TerrainTest() {
		
		sng = new SimplexNoise();
	
	}
	
	public void test() {
		
		int[] numbers = sng.getTable();
		
		System.out.println("Hello");
		
		System.out.println(Arrays.toString(numbers));
	}

}
