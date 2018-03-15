package com.jlunchbox.core.ecs;

import com.jlunchbox.core.util.Bag;

public class EntityManager {
	
	static final Bag<EntityType> types = new Bag<EntityType>();
	
	final Bag<Entity> entities;
	
	public EntityManager(int estNumEntities) {
		
		entities = new Bag<Entity>(estNumEntities);
		
	}


}
