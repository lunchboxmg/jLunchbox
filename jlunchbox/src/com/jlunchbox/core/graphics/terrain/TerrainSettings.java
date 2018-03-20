package com.jlunchbox.core.graphics.terrain;

import java.util.Arrays;

public class TerrainSettings {
	
	public float minHeight = 0;
	public float maxHeight = 10;
	public float avgHeight = 0;
	
	private static final float[] DEFAULT = new float[] {1 / 24.0f, 2 / 24.0f, 4 / 24.0f, 8 / 24.0f, 6 / 24.0f, 3 / 24.0f};

	public float[] octaveWeights = new float[] {0, 0, 0, 0, 0, 0};

	public TerrainSettings() { }
	
	public TerrainSettings setDefaults() {
		this.avgHeight = 64.0f;
		this.minHeight = 0.0f;
		this.maxHeight = 256.0f;
		this.octaveWeights = Arrays.copyOf(DEFAULT, 6);
		return this;
	}
	
	public TerrainSettings octaves(float w0, float w1, float w2, float w3, float w4, float w5) {
		
		// Standard weights for the octaves are 1, 2, 4, 8, 6, 3
        float norm = 1 / (1 * w0 + 2 * w1 + 4 * w2 + 8 * w3 + 6 * w4 + 3 * w5);
        this.octaveWeights[0] = w0 * 1 * norm;
        this.octaveWeights[1] = w1 * 2 * norm;
        this.octaveWeights[2] = w2 * 4 * norm;
        this.octaveWeights[3] = w3 * 8 * norm;
        this.octaveWeights[4] = w4 * 6 * norm;
        this.octaveWeights[5] = w5 * 3 * norm;
        return this;
	}

}
