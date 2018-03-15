package com.jlunchbox.core.ecs;

import com.jlunchbox.core.util.Bag;

public class EntityTypeMapper {
	
	private final EntityType type;
	private final Bag<Integer> entities;

	public EntityTypeMapper(EntityType type) {
		
		this.type = type; 
		entities = new Bag<Integer>();
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity.getId());
	}
	
	public EntityType getType() {
		return type;
	}

}
