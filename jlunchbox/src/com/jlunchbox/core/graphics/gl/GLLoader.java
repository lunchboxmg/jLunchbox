package com.jlunchbox.core.graphics.gl;

public class GLLoader {

	private GLLoader() { }

	public static Mesh createInterleaved(int vertexCount, float[]...data) {

		// Calculate the dimensions of each input data array
		// Calculate the stride of the interleaved data
		int[] dims = new int[data.length];
		int stride = 0;
		for (int i = 0; i < data.length; i++) {
			int dim = data[i].length / vertexCount;
			dims[i] = dim;
			stride += dim;
		}
		
		// Interleave the input data arrays
		float[] interleaved = new float[stride*vertexCount];
		int p = 0;
		for (int i = 0; i < vertexCount; i++) {
			for (int d = 0; d < data.length; d++) {
				int dim = dims[d];
				for (int x = 0; x < dim; x++) {
					interleaved[p++] = data[d][i*dim + x];
				}
			}
		}
		
		// Store the data in a mesh
		return new Mesh(vertexCount, interleaved, dims, stride);
	}

}
