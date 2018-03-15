package com.jlunchbox.test.animals;

import com.jlunchbox.core.ecs.Component;
import com.jlunchbox.core.ecs.ComponentBlueprint;
import com.jlunchbox.core.ecs.ComponentType;

public class DiceBlueprint extends ComponentBlueprint{
	
	private int num;
	private int sides;
	private int base;

	public DiceBlueprint(ComponentType type, int num, int sides, int base) {
		super(type);
		this.num = num;
		this.sides = sides;
		this.base = base;
	}
	
	public int rollDice() {
		int result = 0;
		for (int i = 0; i < num; i++)
			result += AnimalsTest.getRandom(sides) + 1;
		return result + base;
	}

	@Override
	public Component create() { return null; }

}
