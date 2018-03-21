package com.jlunchbox.core.graphics.terrain;

import com.jlunchbox.core.graphics.gl.Shader;
import com.jlunchbox.core.graphics.gl.UniformMatrix;

public class TerrainShader extends Shader {
	
	private static final String VERTEX_SHADER = "terrainVertex.glsl";
	private static final String FRAGMENT_SHADER = "terrainFragment.glsl";

	protected UniformMatrix matProj = new UniformMatrix("matProj");
	protected UniformMatrix matView = new UniformMatrix("matView");
	
	
	public TerrainShader() {
		super("TerrainShader", VERTEX_SHADER, FRAGMENT_SHADER);
		super.storeLocations(matProj, matView);
	}

}
