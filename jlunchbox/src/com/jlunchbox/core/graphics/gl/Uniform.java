package com.jlunchbox.core.graphics.gl;

import org.lwjgl.opengl.GL20;

public abstract class Uniform {

	private static final int NOT_FOUND = -1;
	
	private String name;
	private int location;
	
	protected Uniform(String name) { this.name = name; }
	
	protected void storeLocation(int progID) {
		location = GL20.glGetUniformLocation(progID, name);
		System.out.printf("%s => Loc: %d\n", name, location);
		if (location == NOT_FOUND)
			System.err.printf("No uniform variable called %s found!", name);
	}
	
	protected int getLocation() { return location; }
	
}