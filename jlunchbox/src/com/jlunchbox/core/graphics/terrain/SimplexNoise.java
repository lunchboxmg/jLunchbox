package com.jlunchbox.core.graphics.terrain;

import java.util.Random;

public class SimplexNoise {
	
	private final Random rnd;
	private int seed;
	
	private int[] table;

	public SimplexNoise() {
		seed = 0;
		rnd = new Random(seed);
		createTable();
	}
	
	public SimplexNoise(int seed) {
		this.seed = seed;
		rnd = new Random(seed);
		createTable();
	}
	
	private void createTable() {
		table = new int[512];
		for (int i = 0; i < 512; i++) {
			table[i] = i & 255;
		}
		for (int i = 0; i < 512; i++) {
			int change = i + rnd.nextInt(512 - i);
			int temp = table[i];
			table[i] = table[change];
			table[change] = temp;
		}
	}
	
	private static int fastfloor(double x) {
		int xi = (int) x;
		return x < xi ? xi - 1 : xi;
	}
	
	public int[] getTable() { return table; }
	
	

}
