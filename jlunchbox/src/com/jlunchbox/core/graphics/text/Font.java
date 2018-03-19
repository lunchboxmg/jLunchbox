package com.jlunchbox.core.graphics.text;

import java.util.ArrayList;
import java.util.List;

import com.jlunchbox.core.graphics.gl.GLLoader;
import com.jlunchbox.core.graphics.gl.Mesh;
import com.jlunchbox.core.graphics.gl.Texture2D;
import com.jlunchbox.core.graphics.gl.TextureLoader;

public class Font {
	
	protected static final double LINE_HEIGHT = 0.4f;
	protected static final int SPACE_ASCII = 32;
	
	private Texture2D texture;
	private FontMetaData metadata;
	
	public Font(String sheetFileName, String dataFileName) {
		texture = TextureLoader.loadTexture2D(sheetFileName);
		metadata = new FontMetaData(dataFileName);
	}

	public Mesh createMesh(Text text) {
		List<Line> lines = buildStructure(text);
		return loadStructure(text, lines);
	}
	
	private List<Line> buildStructure(Text text) {
		
		char[] chars = text.getCharArray();
		List<Line> lines = new ArrayList<Line>();
		Line currentLine = new Line(metadata.getWidthSpace(), text.getFontsize(), text.getMaxSizeLine());
		Word currentWord = new Word(text.getFontsize());
		
		for (char c : chars) {
			int ascii = (int) c;
			if (ascii == SPACE_ASCII) { // Word is done, try to add it
				boolean added = currentLine.addWord(currentWord);
				if (!added) { // the Word made the line too long
					lines.add(currentLine);
					currentLine = new Line(metadata.getWidthSpace(), text.getFontsize(), text.getMaxSizeLine());
					currentLine.addWord(currentWord); // may need to check if the word istelf is longer than the line
				}
				currentWord = new Word(text.getFontsize());
				continue;
			}
			currentWord.add(getCharacter(ascii));
		} // Must handle the last word
		boolean added = currentLine.addWord(currentWord);
		if (!added) {
			lines.add(currentLine);
			currentLine = new Line(metadata.getWidthSpace(), text.getFontsize(), text.getMaxSizeLine());
			currentLine.addWord(currentWord);
			lines.add(currentLine);
		}
		return lines;
	}
	
	public Mesh loadStructure(Text text, List<Line> lines) {
	
		double indent = 0;
		double xCursor = 0;
		double yCursor = 0;
		
		List<Float> vertices = new ArrayList<Float>();
		List<Float> texCoords = new ArrayList<Float>();
		
		for (Line line : lines) {
			xCursor = indent; // TODO: Add alignment here
			for (Word word: line.getWords()) {
				for (Character letter : word.getLetters()) {
					load(xCursor, yCursor, letter, text.getFontsize(), vertices);
					append(texCoords, letter.getXTex(), letter.getYTex(), letter.getXTexMax(), letter.getYTexMax());
					xCursor += letter.getxAdvance() * text.getFontsize();
				}
				xCursor += metadata.getWidthSpace() * text.getFontsize();
			}
			yCursor += LINE_HEIGHT * text.getFontsize();
		}
		float[] vertArray = toArray(vertices);
		float[] texArray = toArray(texCoords);
		// LOAD TO A VAO
		// SAVE MESH INFO
		return GLLoader.createInterleaved(vertices.size()/2, vertArray, texArray);
	}
	
	private void load(double xCursor, double yCursor, 
			Character letter, double fontsize, List<Float> vertices) {
		double x = xCursor + (letter.getXOffset() * fontsize);
		double y = yCursor + (letter.getYOffset() * fontsize);
		double xMax = x + (letter.getXSize()*fontsize);
		double yMax = x + (letter.getXSize()*fontsize);
		append(vertices, x, y, xMax, yMax);
	}
	
	private void append(List<Float> list, double x, double y, double xMax, double yMax) {
		list.add((float) x);
		list.add((float) y);
		list.add((float) x);
		list.add((float) yMax);
		list.add((float) xMax);
		list.add((float) yMax);
		list.add((float) xMax);
		list.add((float) yMax);
		list.add((float) xMax);
		list.add((float) y);
		list.add((float) x);
		list.add((float) y);
	}
	
	private float[] toArray(List<Float> list) {
		float[] array = new float[list.size()];
		for (int i = 0; i < array.length; i++)
			array[i] = list.get(i);
		return array;
	}

	// GETTERS //
	public int getTextureID() { return texture.getID(); }
	public Character getCharacter(int ascii) { return metadata.getCharacter(ascii); }
}
