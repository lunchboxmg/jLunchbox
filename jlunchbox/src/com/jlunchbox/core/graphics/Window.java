package com.jlunchbox.core.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

/** Creates the display window.
 * @author lunchboxmg
 */
public class Window {

	// Window sizing and updating options
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final boolean V_SYNC = true;
	
	// Timing members
	public static double timeCurrent;
	public static double timePrevious;
	public static double timeDelta;
	public static double fps;
	public static long frameCount;

	// Window members
	private static long id; // hDC for the window
	private static int width;
	private static int height;
	private static float aspect;
	private static boolean closeRequested = false;

	private Window() { }

	public static void create(String title) { create(title, WIDTH, HEIGHT); }
	
	public static void create(String title, int width, int height) {
		
		// Set up the window context
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		//glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		
		// Create the window context
		id = glfwCreateWindow(width, height, "Test Window!", NULL, NULL);
		if ( id == NULL )
			throw new RuntimeException("Failed to create the GLFW window");
		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(id, pWidth, pHeight);
			System.out.printf("Window created with size (%d x %d)%n", pWidth.get(0), pHeight.get(0));

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(id,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(id);

		// Enable v-sync
		if ( V_SYNC )
			glfwSwapInterval(1);
		else
			glfwSwapInterval(0);

		// Make the window visible
		glfwShowWindow(id);		
		
		//GL11.glViewport(0, 0, WIDTH, HEIGHT);
		setDimensions(width, height);
	}
		
	public static void update() {
		double timeGPU = getTimeGPU();
		timeDelta = timeGPU - timePrevious;
		timePrevious = timeCurrent;
		timeCurrent = timeGPU;
		fps = 1.0 / timeDelta;
		frameCount++;
	}
	
	public static void swap() { glfwSwapBuffers(id); }
	
	public static void close() { glfwWindowShouldClose(id); }
	
	public static void requestClose() { closeRequested = true; }
	
	public static boolean isCloseRequested() { return closeRequested; }

	public static boolean isOpen() {
		return !glfwWindowShouldClose(id) && !closeRequested;
	}

	public static void destroy() { glfwDestroyWindow(id); }

	private static double getTimeGPU() { return glfwGetTime(); }
	
	// GETTERS
	
	public static long getID() { return id; }
	public static int getWidth() { return width; }
	public static int getHeight() { return height; }
	public static float getAspectRatio() { return aspect; }

	public static double getTimeCurrent() { return timeCurrent; }
	public static double getTimePrevious() { return timePrevious; }
	public static double getTimeDelta() { return timeDelta; }
	public static long getFrameCount() { return frameCount; }
	public static double getFPS() { return fps; }

	// SETTERS
	
	private static void setDimensions(int w, int h) {
		width = w;
		height = h;
		aspect = (float) w / (float) h;
	}

}
