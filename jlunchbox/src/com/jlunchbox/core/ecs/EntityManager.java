package com.jlunchbox.core.ecs;

import com.jlunchbox.core.util.Bag;

public class EntityManager {
	
	static final Bag<EntityType> types = new Bag<EntityType>();
	
	private final Bag<Entity> entities;
	private int nextId = 0;
	
	public EntityManager(int estNumEntities) {
		
		entities = new Bag<Entity>(estNumEntities);
		
	}
	
	protected int create() {
		return nextId++;
	}


}
