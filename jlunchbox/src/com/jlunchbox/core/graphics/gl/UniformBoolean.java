package com.jlunchbox.core.graphics.gl;

import org.lwjgl.opengl.GL20;

public class UniformBoolean extends Uniform {
	
	private boolean value;
	private boolean used;
	
	public UniformBoolean(String name) { super(name); }
	
	public void load(boolean value) {
		if (!used || this.value != value) {
			GL20.glUniform1f(super.getLocation(), value ? 1f : 0f);
			used = true;
			this.value = value;
		}
	}

}
