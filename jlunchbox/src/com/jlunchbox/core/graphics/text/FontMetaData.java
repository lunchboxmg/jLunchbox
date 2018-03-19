package com.jlunchbox.core.graphics.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.jlunchbox.core.graphics.Window;

import java.util.HashMap;

public class FontMetaData {
	
	private static final int PAD_TOP = 0;
	private static final int PAD_LEFT = 1;
	private static final int PAD_BOTTOM = 2;
	private static final int PAD_RIGHT = 3;

	private static final int DESIRED_PADDING = 10;
	
	private double vpps, hpps, widthSpace;
	
	private int[] padding;
	private int paddingWidth;
	private int paddingHeight;
	
	private BufferedReader reader;
	private Map<String, String> values = new HashMap<String, String>();
	private Map<Integer, Character> data = new HashMap<Integer, Character>();

	public FontMetaData (String filename) {
		openBuffer(filename);
		loadPadding();
		loadLineSizes();
		loadData();
		closeBuffer(filename);
	}
	
	private void openBuffer(String filename) {
		reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (Exception e) {
			System.err.printf("Couldn't get reader for [%s]", filename);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private void closeBuffer(String filename) {
		try { reader.close(); }
		catch (Exception e) {
			e.printStackTrace();
			System.err.printf("Couldn't close reader for [%s]", filename);
		}
	}
	
	private boolean processLine() {
		values.clear();
		String line = null;
		try { line = reader.readLine(); }
		catch (IOException e) {}
		if (line == null ) { return false; }
		
		for (String part : line.split(" ")) {
			String[] pairs = part.split("=");
			if (pairs.length == 2) { values.put(pairs[0], pairs[1]); }
		}
		return true;
	}
	
	private void loadPadding() {
		processLine();
		padding = parseInts("padding");
		paddingWidth = padding[PAD_LEFT] + padding[PAD_RIGHT];
		paddingHeight = padding[PAD_TOP] + padding[PAD_BOTTOM];
	}
	
	private void loadLineSizes() {
		processLine();
		int lineHeightPixels = parseInt("lineHeight") - paddingHeight;
		vpps = 0.04f / (double) lineHeightPixels;
		hpps = vpps / Window.getAspectRatio();
	}
	
	private void loadData() {
		int widthImage = parseInt("scaleW"); 
		processLine(); processLine();
		while (processLine()) {
			Character c = loadCharacter(widthImage);
			if (c != null) {data.put(c.getId(), c); }
		}
	}
		
	private Character loadCharacter(int sizeImage) {
		int id = parseInt("id");
		if (id == 32) {
			widthSpace = (parseInt("xadvance") - paddingWidth) * hpps;
			return null;
		}
		
		double xTex = ((double) parseInt("x") + (padding[PAD_LEFT] - DESIRED_PADDING)) / sizeImage;
		double yTex = ((double) parseInt("y") + (padding[PAD_TOP] - DESIRED_PADDING)) / sizeImage;
		int width = parseInt("width") - (paddingWidth - (2 * DESIRED_PADDING));
		int height = parseInt("height") - (paddingHeight - (2 * DESIRED_PADDING));
		double quadWidth = width * hpps;
		double quadHeight = height * vpps;
		double xTexSize = (double) width / sizeImage;
		double yTexSize = (double) height / sizeImage;
		double xOffset = (parseInt("xoffset") + padding[PAD_LEFT] - DESIRED_PADDING) * hpps;
		double yOffset = (parseInt("yoffset") + padding[PAD_TOP] - DESIRED_PADDING) * vpps;
		double xAdvance = (parseInt("xadvance") - paddingWidth) * hpps;
		
		return new Character(id, xTex, xTexSize, xOffset, quadWidth, 
				yTex, yTexSize, yOffset, quadHeight, xAdvance);
	}
	
	private int[] parseInts(String variable) {
		String[] parts = values.get(variable).split(",");
		int[] numbers = new int[parts.length];
		for(int i = 0; i < parts.length; i++) {
			numbers[i] = Integer.parseInt(parts[i]);
		}
		return numbers;
	}
	
	private int parseInt(String variable) {
		return Integer.parseInt(values.get(variable));
	}
	
	private String parseString(String variable) {
		return values.get(variable).replaceAll("\"", "");
	}

	// GETTERS //
	protected Character getCharacter(int ascii) { return data.get(ascii); }
	protected double getWidthSpace() { return widthSpace; }
	
}
