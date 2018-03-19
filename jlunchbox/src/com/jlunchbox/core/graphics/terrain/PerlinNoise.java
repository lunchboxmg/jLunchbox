package com.jlunchbox.core.graphics.terrain;

import java.util.Random;

public class PerlinNoise {
	
	private final int seed;

	public PerlinNoise(int seed) {
		this.seed = seed;
	}
	
	public PerlinNoise() {
		seed = new Random().nextInt(Integer.MAX_VALUE);
	}
	
	
	public int getSeed() { return seed; }

}
