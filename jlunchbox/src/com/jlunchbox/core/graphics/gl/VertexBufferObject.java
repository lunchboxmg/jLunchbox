package com.jlunchbox.core.graphics.gl;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL20;

public class VertexBufferObject {

	private final int id;
	private int tempTarget;
	
	public VertexBufferObject() {
		id = glGenBuffers();
	}
	
	public void bind(int target) { glBindBuffer(target, id); tempTarget = target; }
	
	public void unbind() { glBindBuffer(tempTarget, 0); }

	public void uploadData(int target, FloatBuffer data, int usage) {
		glBufferData(target, data, usage);
	}
	
	public void uploadData(int target, IntBuffer data, int usage) {
		glBufferData(target, data, usage);
	}
	
	public void uploadData(int target, long size, int usage) {
		glBufferData(target, size, usage);
	}
	
	public void uploadSubData(int target, long offset, FloatBuffer data) {
		glBufferSubData(target, offset, data);
	}
	
	public static void enable(int numAttribs) {
		for (int i = 0; i < numAttribs; i++)
			GL20.glEnableVertexAttribArray(i);
	}
	
	public void delete() { glDeleteBuffers(id); }
	
	// GETTERS
	public int getID() { return id; }

}
