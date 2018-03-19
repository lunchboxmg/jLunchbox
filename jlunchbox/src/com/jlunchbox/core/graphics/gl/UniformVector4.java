package com.jlunchbox.core.graphics.gl;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;

public class UniformVector4 extends Uniform {
	
	// This will hardly be used to don't waste time atm for
	// extensive usage checking.
	
	public UniformVector4(String name) { super(name); }
	
	public void load(Vector4f vector) {
		load(vector.x, vector.y, vector.z, vector.w);
	}
	
	public void load(float x, float y, float z, float w) {
		GL20.glUniform4f(super.getLocation(), x, y, z, w);
	}

}
