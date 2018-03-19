package com.jlunchbox.core.graphics.gl;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;


public class Shader {
	
	private int progID;

	public Shader(String name, String vertFile, String fragFile) {
		
		int vShaderID = loadShader(vertFile, GL_VERTEX_SHADER);
		int fShaderID = loadShader(fragFile, GL_FRAGMENT_SHADER);

		progID = glCreateProgram();
		glAttachShader(progID, vShaderID);
		glAttachShader(progID, fShaderID);
		glLinkProgram(progID);
		glDetachShader(progID, vShaderID);
		glDetachShader(progID, fShaderID);
		glDeleteShader(vShaderID);
		glDeleteShader(fShaderID);
	}
	
	public void start() { glUseProgram(this.progID); }
	public void stop() { glUseProgram(0); }

	public void clean() {
		glUseProgram(0);
		glDeleteProgram(progID);
	}

	protected void storeLocations(Uniform... uniforms) {
		for (Uniform uniform : uniforms)
			uniform.storeLocation(progID);
		glValidateProgram(progID);
	}
	
	private static String loadSource(String filename) {
		
		StringBuilder source = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String buffer = "";
			while ((buffer = reader.readLine()) != null)
				source.append(buffer).append("\n");
			reader.close();
		} catch (Exception e) {
			System.err.printf("Couldn't load shader source [%s]", filename);
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.print(source.toString());
		return source.toString();
	}

	private int loadShader(String filename, int type) {

		String source = loadSource(filename);
		int shaderID = glCreateShader(type);

		glShaderSource(shaderID, source);
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderID, 500));
			System.err.printf("Could not compile shader from [%s]", filename);
			System.exit(-1);
		}
		
		return shaderID;
	}

	private void bindAttrib(int attrib, String name) {
		glBindAttribLocation(this.progID, attrib, name);
	}

	
}
