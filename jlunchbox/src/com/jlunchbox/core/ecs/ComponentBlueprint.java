package com.jlunchbox.core.ecs;

public abstract class ComponentBlueprint { 
	
	protected ComponentType type;
	
	public ComponentBlueprint(ComponentType type) {
		this.type = type;
	}

	public Component create() {
		try {
			return type.getBase().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	public ComponentType getComponentType() { return type; }

}
