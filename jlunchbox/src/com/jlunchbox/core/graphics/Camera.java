package com.jlunchbox.core.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
	
	private static final Vector3f WORLD_UP = new Vector3f(0, 1, 1);
	
	private Vector3f position;
	private Vector3f target;
	private Matrix4f view;

	public Camera() {
		this(new Vector3f(0, 0, 0));
	}
	
	public Camera(Vector3f target) {
		this.target = target;
		create();
	}
	
	private void create() {
		this.position = new Vector3f();
		this.view = new Matrix4f();
		update();
	}
	
	public void update() {
		
		// Constrain parameters
		// Calculate new position
		// Update view matrix
		view.lookAt(position, target, WORLD_UP, view);
		
	}
	
	// GETTERS
	
	public Vector3f getPosition() { return position; }
	public Vector3f getTarget() { return target; }
	public Matrix4f getView() { return view; }

}
