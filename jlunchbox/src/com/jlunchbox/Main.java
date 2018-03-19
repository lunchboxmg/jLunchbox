package com.jlunchbox;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import com.jlunchbox.core.input.Keyboard;
import com.jlunchbox.core.input.Mouse;
import com.jlunchbox.test.animals.AnimalsTest;
import com.jlunchbox.core.graphics.Window;
import com.jlunchbox.core.graphics.model.ObjLoader;

public class Main {

	public Main() { }

	public static void main(String[] args) { init(); run(); kill(); } 
	
	public static void init() {

		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initailized GLFW.");

		// Create I/O elements
		Window.create("Test Window");
		Keyboard.create();
		Mouse.create();

		// Must do this before running OpenGL functions, but after
		// window creation!
		GL.createCapabilities(); // VERY IMPORTANT - DO NOT REMOVE!
		glViewport(0, 0, Window.getWidth(), Window.getHeight());

		test();
		
	}
	
	private static void update() {

		Window.update();
		Keyboard.update();
		Mouse.update();
		glfwPollEvents();
		if (Mouse.didScroll())
			System.out.println("SCROLLED!");
		if (Mouse.isButtonReleased(Mouse.RIGHT))
			System.out.println("RIGHT RELEASED!");
	}
	
	public static void run() {

		while(Window.isOpen()) {
			update();
			//Mouse.output();
			if (Keyboard.isKeyPressed(GLFW_KEY_ESCAPE)) {
				System.out.println("Hi!");
				Window.requestClose();
			}
			Window.swap();
		}
	}
	
	public static void kill() {
		
		Window.close();
		Window.destroy();
		glfwTerminate();
	}
	
	public static void test() {
		
		AnimalsTest tester = new AnimalsTest();
		
		tester.test();
		
		ObjLoader loader = new ObjLoader();
		loader.load("G:\\Alpha\\res\\models\\birch1.obj");

	}

}
