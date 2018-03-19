package com.jlunchbox.core.graphics.text;

import com.jlunchbox.core.graphics.gl.Mesh;

public class TextManager {

	
	private TextManager() { }
	
	public static void createText(String msg, Font font, float fontsize, float maxSizeLine) {
		
		Text text = new Text(msg, font, fontsize, maxSizeLine);
		Mesh mesh = font.createMesh(text);
		
	}

}
