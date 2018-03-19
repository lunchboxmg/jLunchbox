package com.jlunchbox.core.graphics.text;

import java.util.ArrayList;
import java.util.List;

public class Word {
	
	private List<Character> letters = new ArrayList<Character>();
	private double width = 0;
	private double fontsize;
	
	public Word(double fontsize) { this.fontsize = fontsize; }
	
	public void add(Character letter) {
		letters.add(letter);
		width += letter.getxAdvance()*fontsize;
	}
	
	// GETTERS //

	public List<Character> getLetters() { return letters; }
	public double getWidth() { return width; }

}
