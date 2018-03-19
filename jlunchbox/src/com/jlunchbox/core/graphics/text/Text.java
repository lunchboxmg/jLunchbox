package com.jlunchbox.core.graphics.text;

public class Text {

	private String text;
	private Font font;
	private float fontsize;
	private float maxSizeLine;

	public Text(String text, Font font, float fontsize, float maxSizeLine) {
		this.text = text;
		this.font = font;
		this.fontsize = fontsize;
		this.maxSizeLine = maxSizeLine;
 	}
	
	// GETTERS //
	public String getText() { return text; }
	public char[] getCharArray() { return text.toCharArray(); }
	public Font getFont() { return font; }
	public float getFontsize() { return fontsize; }
	public float getMaxSizeLine() { return maxSizeLine; }

}
