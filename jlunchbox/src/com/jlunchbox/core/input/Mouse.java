package com.jlunchbox.core.input;

import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import com.jlunchbox.core.graphics.Window;

import java.nio.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;

public class Mouse {

	public static final int LEFT = GLFW_MOUSE_BUTTON_LEFT;
	public static final int MIDDLE = GLFW_MOUSE_BUTTON_MIDDLE;
	public static final int RIGHT = GLFW_MOUSE_BUTTON_RIGHT;

	private static ArrayList<Integer> buttonsPressed = new ArrayList<Integer>();
	private static ArrayList<Integer> buttonsHeld = new ArrayList<Integer>();
	private static ArrayList<Integer> buttonsReleased = new ArrayList<Integer>();
	private static int modBitField = 0;
	
	private static float scrollOffsetX = 0.0f;
	private static float scrollOffsetY = 0.0f;
	
	private static float x;
	private static float y;
	private static float dx;
	private static float dy;

	private Mouse() { }
	
	public static void create() {
		
		GLFWMouseButtonCallback buttonCallback = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				
				if (action == GLFW_PRESS) {
					if (!buttonsPressed.contains(button)) {
						buttonsPressed.add(button);
						buttonsHeld.add(button);
					}
				}
				if (action == GLFW_RELEASE) {
					buttonsHeld.remove(new Integer(button));
					buttonsReleased.add(button);
				}
				modBitField = mods;
			}
		};
		glfwSetMouseButtonCallback(Window.getID(), buttonCallback);
		
		GLFWScrollCallback scrollCallback = new GLFWScrollCallback() {
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				scrollOffsetX = (float) xoffset;
				scrollOffsetY = (float) yoffset;
			}
		};
		glfwSetScrollCallback(Window.getID(), scrollCallback);
	}
	
	public static void update() {
		buttonsPressed.clear();
		buttonsReleased.clear();
		scrollOffsetX = 0.0f;
		scrollOffsetY = 0.0f;
		updatePosition();
	}
	
	private static void updatePosition() {
		try ( MemoryStack stack = stackPush() ) {
			DoubleBuffer buffx = stack.mallocDouble(1);
			DoubleBuffer buffy = stack.mallocDouble(1);
			glfwGetCursorPos(Window.getID(), buffx, buffy);
			float newx = (float) buffx.get(0);
			float newy = (float) buffy.get(0);
			dx = newx - x;
			dy = newy - y;
			x = newx;
			y = newy;
		}
	}
	
	public static void output() {
		System.out.printf("Mouse Current X, Y: %.2f, %.2f\nMouse moved by: %.2f, %.2f\n", x, y, dx, dy);
	}
	
	// GETTERS - Button Interactions
	
	public static boolean isButtonPressed(int button) { return buttonsPressed.contains(button); }
	public static boolean isButtonHeld(int button) { return buttonsHeld.contains(button); }
	public static boolean isButtonReleased(int button) { return buttonsReleased.contains(button); }
	public static boolean isModPressed(int key) { return (modBitField & key) == key; }
	
	// GETTERS - Mouse Position and Movement

	public static float getX() { return x; }
	public static float getY() { return y; }
	public static float getDX() { return dx; }
	public static float getDY() { return dy; }
	public static boolean didMove() { return (dx != 0.0) || (dy != 0.0); }

	// GETTERS - Mouse Wheel Scrolling

	public static float getScrollX() { return scrollOffsetX; }
	public static float getScrollY() { return scrollOffsetY; }
	public static boolean didScroll() { return (scrollOffsetX != 0.0) || (scrollOffsetY != 0.0); }
	
}
