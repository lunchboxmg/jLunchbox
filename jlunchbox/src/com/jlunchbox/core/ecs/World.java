package com.jlunchbox.core.ecs;

public class World {
	
	private final ComponentManager cm;
	private final EntityManager em;

	public World(int estNumEntities) {
		
		cm = new ComponentManager();
		em = new EntityManager(estNumEntities);
	}
	
	public ComponentManager getComponentManager() { return cm; }
	
	public EntityManager getEntityManager() { return em; }

}
