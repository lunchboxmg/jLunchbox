package com.jlunchbox.test.animals;

public class PositionBlueprint {

	public PositionBlueprint() { }
	
	public PositionComponent create() {

		PositionComponent component = new PositionComponent();
		component.position.x = 10.0f;
		component.position.y = 8.0f;
		return component;
	}

}
