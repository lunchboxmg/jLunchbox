package com.jlunchbox.core.graphics.text;

import java.util.ArrayList;
import java.util.List;

public class Line {
	
	private List<Word> words = new ArrayList<Word>();
	private double width = 0;
	private double widthMax;
	private double widthSpace;
	
	
	public Line(double fontsize, double maxWidth, double spaceWidth) {
		this.widthSpace = spaceWidth*fontsize;
		this.widthMax = maxWidth;
	}
	
	public boolean addWord(Word word) {
		double widthWord = word.getWidth();
		if (words.size() > 0)
			widthWord += widthSpace;
		if (width + widthWord <= widthMax) {
			words.add(word);
			width += widthWord;
			return true;
		} else {
			return false;
		}
	}
	
	// Getters
	public List<Word> getWords() { return words; }
	public double getWidth() { return width; }
	public double getMaxWidth() { return widthMax; }

}
