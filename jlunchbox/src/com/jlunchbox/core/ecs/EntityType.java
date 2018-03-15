package com.jlunchbox.core.ecs;

/**
 * EntityType presents is a base entity than can construct new
 * entities based on the Component Blueprints specific to this
 * EntityType.
 * 
 * Ex: EntityType[Dog] = ComponentBlueprints[Name, Health, Transform ...]
 * 
 * @author lunchboxmg
 *
 */
public class EntityType {
	
	private static int count = 0; // counter for creating indexes of new types
	private final int index;
	
	private ComponentType type;
	private ComponentBlueprint blueprint;
	
	public EntityType() {
		index = count++;
	}
	
	public int getIndex() {	return index; }
	
	public void addComponent(ComponentType componentType, ComponentBlueprint blueprint) {
		this.type = componentType;
		this.blueprint = blueprint;
	}
	
	public Component create() {
		return this.blueprint.create();
	}

}
