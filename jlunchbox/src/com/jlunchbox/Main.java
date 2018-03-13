package com.jlunchbox;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import com.jlunchbox.core.graphics.Window;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void init() {

		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initailized GLFW.");

		// Create I/O elements
		Window.create("Test Window");

		// Must do this before running OpenGL functions, but after
		// window creation!
		GL.createCapabilities(); // VERY IMPORTANT - DO NOT REMOVE!
		glViewport(0, 0, Window.getWidth(), Window.getHeight());

	}
	
	public static void run() {
		
	}
	
	public static void kill() {
		
		Window.close();
		glfwTerminate();
	}

}
