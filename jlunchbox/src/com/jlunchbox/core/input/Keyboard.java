package com.jlunchbox.core.input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWKeyCallback;

import com.jlunchbox.core.graphics.Window;

public class Keyboard {

	private static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
	private static ArrayList<Integer> keysHeld = new ArrayList<Integer>();
	private static ArrayList<Integer> keysReleased = new ArrayList<Integer>();
	private static int modBitField = 0;
	
	private Keyboard() { }
	
	public static void create() {

		GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				
				if (key >= 0 && key < 1024) {
					if (action == GLFW_PRESS) {
						if (!keysPressed.contains(key)) {
							keysPressed.add(key);
							keysHeld.add(key);
							if (key == GLFW_KEY_ESCAPE) {
								System.out.print("Escape pressed!");
							}
						}
					}
					if (action == GLFW_RELEASE) {
						keysHeld.remove(new Integer(key));
						keysReleased.add(key);
					}
					modBitField = mods;
				}
			}
		};
		glfwSetKeyCallback(Window.getID(), keyCallback); 
		
	}
	
	public static void update() {
		
		keysPressed.clear();
		keysReleased.clear();
		// TODO: May need to clear modifier bit-field attribute also
	}
	
	// GETTERS - Key Interactions
	
	public static boolean isKeyPressed(int key) { return keysPressed.contains(key); }
	public static boolean isKeyHeld(int key) { return keysHeld.contains(key); }
	public static boolean isKeyReleased(int key) { return keysReleased.contains(key); }
	public static boolean isModPressed(int key)  { return (modBitField & key) == key; }

	// GETTERS - Key Lists
	
	public static ArrayList<Integer> getKeysPressed() { return keysPressed; }
	public static ArrayList<Integer> getKeysHeld() { return keysHeld; }
	public static ArrayList<Integer> getKeysReleased() { return keysReleased; }

}
