package com.jlunchbox.core.ecs;

public class World {
	
	private final ComponentManager cm;

	public World() {
		
		cm = new ComponentManager();
		
	}
	
	public ComponentManager getComponentManager() { return cm; } 

}
