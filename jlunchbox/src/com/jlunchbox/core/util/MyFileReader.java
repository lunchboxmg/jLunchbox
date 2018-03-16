package com.jlunchbox.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.joml.Vector3f;

public class MyFileReader {

	private BufferedReader reader;

	private String line;
	private String[] items;
	private int index;
	
	private String delim;

	public MyFileReader(String filename, String delim) throws Exception {
		try {
			InputStream input = Class.class.getResourceAsStream(filename);
			InputStreamReader stream = new InputStreamReader(input);
			reader = new BufferedReader(stream);
		} catch (Exception e) {
			System.err.println("Couldn't get reader for " + filename);
			throw e;
		}
		this.delim = delim;
	}
	
	public String nextLine() {
		String newLine = null;
		try {
			newLine = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (newLine != null) {
			line = newLine;
			items = newLine.split(delim);
			index = 0;
			return newLine;
		} else {
			return null;
		}
	}
	
	public boolean isEOL() { return index < items.length; }
	
	public String getLine() { return line; }
	public String[] getLineData() {return items;}
	public int getLenLineData() { return items.length; }

	public float getNextFloat() { return Float.parseFloat(items[index++]); }
	public int getNextInt() { return Integer.parseInt(items[index++]); }
	public long getNextLong() { return Long.parseLong(items[index++]); }
	
	public float[] getNextFloats(int amount) {

		if (index + amount >= items.length)
			amount = items.length - index;

		float[] r = new float[amount];
		for(int i=0; i<amount; i++)
			r[i] = getNextFloat();

		return r;
	}
	
	public Vector3f getNextVector3f() {
		float x = getNextFloat();
		float y = getNextFloat();
		float z = getNextFloat();
		return new Vector3f(x, y, z);
	}
}