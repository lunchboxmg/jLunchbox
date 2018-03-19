package com.jlunchbox.core.graphics.text;

public class Character {

	private int id;
	private double xTex;
	private double xTexMax;
	private double xOffset;
	private double xSize;
	private double yTex;
	private double yTexMax;
	private double yOffset;
	private double ySize;
	private double xAdvance;

	public Character(int id, double xTex, double xTexMax, double xOffset, double xSize, double yTex, double yTexMax,
			double yOffset, double ySize, double xAdvance) {

		this.id = id;
		this.xTex = xTex;
		this.xTexMax = xTexMax;
		this.xOffset = xOffset;
		this.xSize = xSize;
		this.yTex = yTex;
		this.yTexMax = yTexMax;
		this.yOffset = yOffset;
		this.ySize = ySize;
		this.xAdvance = xAdvance;
	}

	// GETTERS //

	public int getId() { return id; }

	public double getXTex() { return xTex; }
	public double getXTexMax() { return xTexMax; }
	public double getXOffset() { return xOffset; }
	public double getXSize() { return xSize; }

	public double getYTex() { return yTex; }
	public double getYTexMax() { return yTexMax; }
	public double getYOffset() { return yOffset; }
	public double getYSize() { return ySize; }

	public double getxAdvance() { return xAdvance; }

}
