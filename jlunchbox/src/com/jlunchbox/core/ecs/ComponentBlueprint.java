package com.jlunchbox.core.ecs;

public abstract class ComponentBlueprint { 
	
	protected ComponentType type;
	
	public ComponentBlueprint(ComponentType type) {
		this.type = type;
	}

	public abstract Component create();
	
	public ComponentType getComponentType() { return type; }

}
