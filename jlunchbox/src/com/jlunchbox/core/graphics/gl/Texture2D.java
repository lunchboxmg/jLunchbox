package com.jlunchbox.core.graphics.gl;

import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;

public class Texture2D {
	
	private int id, width, height;
	
	public Texture2D(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
		System.out.printf("Texture loaded %d, %d\n", width, height);
	}
	
	public void load(ByteBuffer buffer) {
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	}
	
	public void bind() { glBindTexture(GL_TEXTURE_2D, id); };
	public void unbind() { glBindTexture(GL_TEXTURE_2D, 0); }
	
	public void delete() { glDeleteTextures(id); }
	
	public int getID() { return id; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }

}
