package com.jlunchbox.core.ecs;

import com.jlunchbox.core.util.Bag;

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
	
	private Bag<ComponentBlueprint> blueprints;
	
	public EntityType() {
		index = count++;
		blueprints = new Bag<ComponentBlueprint>();
	}
	
	public int getIndex() {	return index; }
	
	public void addComponent(ComponentType componentType, ComponentBlueprint blueprint) {
		this.type = componentType;
		this.blueprint = blueprint;
		blueprints.add(blueprint);
	}
	
	public Component create() {
		return this.blueprint.create();
	}
	
	public void createNew(World world) {
		int entityId = world.getEntityManager().create();
		ComponentManager cm = world.getComponentManager();
		ComponentBlueprint b = null;
		for (int i = 0; i < blueprints.size(); i++) {
			// Create component from blueprint
			b = blueprints.get(i);
			cm.getMapper(b.type.getBase()).add(entityId, b.create());;
		}
	}

}
