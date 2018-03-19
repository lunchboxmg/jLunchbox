package com.jlunchbox.core.graphics.gl;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
	
	private VertexArrayObject vao = new VertexArrayObject();
	private List<VertexBufferObject> vbos = new ArrayList<VertexBufferObject>();
	private int numAttribs;
	private int vertexCount;

	public Mesh(int vertexCount, float[] interleaved, int[] dims, int stride) {

		VertexBufferObject vbo = new VertexBufferObject();
		vao.bind();
		vbo.bind(GL15.GL_ARRAY_BUFFER);
		vbo.uploadData(GL15.GL_ARRAY_BUFFER, loadBuffer(interleaved), GL15.GL_STATIC_DRAW);
		
		int memSize = stride*4;
		int offset = 0;
		for (int i = 0; i < dims.length; i++) {
			GL20.glVertexAttribPointer(i, dims[i], GL11.GL_FLOAT, false, memSize, offset*4);
			offset += dims[i];
		}
		numAttribs = dims.length;
		
		vbo.unbind();
		vao.unbind();
		vbos.add(vbo);
	}
	
	private static FloatBuffer loadBuffer(float[] data) {
		FloatBuffer buffer = null;
		buffer = MemoryUtil.memAllocFloat(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public void delete() {
		for (VertexBufferObject vbo : vbos)
			vbo.delete();
		vao.delete();
	}

	// HELPER - SHORTCUT //
	
	public void bindVao() { vao.bind(); }
	public void unbindVao() { vao.unbind(); }
	
	// GETTERS //

	public int getVertexCount() { return vertexCount; }
	public int getAttribCount() { return numAttribs; }
}
