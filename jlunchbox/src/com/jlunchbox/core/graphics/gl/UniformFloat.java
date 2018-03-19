package com.jlunchbox.core.graphics.gl;

import org.lwjgl.opengl.GL20;

public class UniformFloat extends Uniform {
	
	private float value;
	private boolean used;
	
	public UniformFloat(String name) { super(name); }
	
	public void load(float value) {
		if (!used || this.value != value) {
			GL20.glUniform1f(super.getLocation(), value);
			used = true;
			this.value = value;
		}
	}

}
