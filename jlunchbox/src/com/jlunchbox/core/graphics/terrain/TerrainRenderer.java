package com.jlunchbox.core.graphics.terrain;

import org.joml.Matrix4f;

import com.jlunchbox.core.graphics.Window;
import com.jlunchbox.core.util.Constants;

public class TerrainRenderer {
	
	private TerrainShader shader;

	public TerrainRenderer() {
		shader = new TerrainShader();
		
		shader.start();
		Matrix4f proj = new Matrix4f().perspective((float) (75.0*Constants.D2R), Window.getAspectRatio(), 0.1f, 100.0f);
		shader.matProj.loadMatrix(proj);
		Matrix4f view = new Matrix4f().lookAt(0.0f, 1.0f, 5.0f,
		         0.0f, 0.0f, 0.0f,
		         0.0f, 1.0f, 0.0f);
		shader.matView.loadMatrix(view);
		shader.stop();
	}
	
	public void render(Chunk[] chunks) {
		for (Chunk chunk : chunks) {
			
		}
	}

}
