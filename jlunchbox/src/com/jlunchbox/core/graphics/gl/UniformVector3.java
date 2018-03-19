package com.jlunchbox.core.graphics.gl;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;

public class UniformVector3 extends Uniform {
	
	private float x;
	private float y;
	private float z;
	private boolean used;

	public UniformVector3(String name) { super(name); }
	
	public void load(Vector3f vector) { 
		load(vector.x, vector.y, vector.z);
	};

	public void load(float x, float y, float z) {
		if (!used || this.x != x || this.y != y || this.z != z) {
			this.x = x;
			this.y = y;
			this.z = z;
			used = true;
			GL20.glUniform3f(super.getLocation(), x, y, z);
		}
	}
}
