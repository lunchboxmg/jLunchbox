package com.jlunchbox.core.graphics.gl;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryStack;

public class TextureLoader {

	private TextureLoader() {}
	
	public static Texture2D loadTexture2D(String filename) {

		ByteBuffer buffer = null;
		IntBuffer width = null;
		IntBuffer height = null;
		
		// Pull data from file into a buffer
		try (MemoryStack stack = MemoryStack.stackPush()) {
			width = stack.mallocInt(1);
			height = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);
			
			stbi_set_flip_vertically_on_load(true);
			buffer = stbi_load(filename, width, height, comp, 4);
			if (buffer == null) {
				throw new RuntimeException(String.format("Failed to load texture %s\n%s", 
						filename, stbi_failure_reason()));
			}
		}
		
		Texture2D texture = new Texture2D(glGenTextures(), width.get(), height.get());

		texture.bind();
		texture.load(buffer);
		texture.unbind();
		
		return texture;
	}
	
}
