package com.jlunchbox.test.animals;

import com.jlunchbox.core.ecs.ComponentType;

public class HealthBlueprint extends DiceBlueprint {
	
	public HealthBlueprint(ComponentType type, int num, int sides, int base) {
		super(type, num, sides, base);
	}
	
	public HealthComponent create() {
		return new HealthComponent(super.rollDice());
	}

}
