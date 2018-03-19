package com.jlunchbox.core.graphics.gl;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

public class UniformMatrix extends Uniform{
	
	// TODO: Look into using lwjgl memory
	private FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
	
	public UniformMatrix(String name) {
		super(name);
	}
	
	public void loadMatrix(Matrix4f matrix){
		matrix.get(buffer);
		GL20.glUniformMatrix4fv(super.getLocation(), false, buffer);
	}

}
