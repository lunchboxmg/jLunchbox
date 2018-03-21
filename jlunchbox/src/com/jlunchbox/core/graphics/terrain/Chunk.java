package com.jlunchbox.core.graphics.terrain;

import java.util.Arrays;

import com.jlunchbox.core.graphics.gl.VertexArrayObject;
import com.jlunchbox.core.graphics.gl.VertexBufferObject;

public class Chunk {
	
	public static final int CHUNK_SIZE = 16;
	public static final int CHUNK_STRIDE = 6;
	
	private final Terrain terrain;
	private final float[] heightMap;
	private final int xPosition;
	private final int zPosition;
	
	private VertexArrayObject vao;
	private VertexBufferObject vbo;

	public Chunk(Terrain terrain, int x, int z) {
		
		this.terrain = terrain;
		this.xPosition = x;
		this.zPosition = z;
		this.heightMap = new float[CHUNK_SIZE * CHUNK_SIZE];
	}
	
	public float getHeightAt(int x, int z) {
		return this.heightMap[z << CHUNK_STRIDE | x];
	}
	
	public Chunk fromData(float[] pre) {
		for (int i = 0; i < heightMap.length; i++)
			heightMap[i] = pre[i];
		return this;
	}
	
	public void genMesh() {
		
		float[] positions = new float[CHUNK_SIZE * CHUNK_SIZE * 3 * 4];
		
		int vindex = 0;
		int pindex = 0;
		for(int i = 0; i < CHUNK_SIZE; i++) {
			for (int j = 0; j < CHUNK_SIZE; j++) {
				positions[pindex++] = xPosition * CHUNK_SIZE + i;
				positions[pindex++] = heightMap[vindex++];
				positions[pindex++] = zPosition * CHUNK_SIZE + j;
			}
		}
		
		System.out.println(Arrays.toString(positions));
		
		//vao = new VertexArrayObject();
		//vbo = new VertexBufferObject();
	}

	public float[] getData() { return heightMap; }

}
