package com.jlunchbox.core.ecs;

import java.util.IdentityHashMap;

import com.jlunchbox.core.util.Bag;
import com.jlunchbox.core.util.ReflectionException;

public class ComponentManager {
	
	// Container that links component classes with component type classes
	private final IdentityHashMap<Class<? extends Component>, ComponentType> typemap = 
			new IdentityHashMap<Class<? extends Component>, ComponentType>();

	// Container of created types
	private final Bag<ComponentType> types = new Bag<ComponentType>();
	
	@SuppressWarnings("rawtypes")
	private final Bag<ComponentMapper> mappers = new Bag<ComponentMapper>();

	public ComponentManager() {

	}
	
	public <T extends Component> T create(int entityId, Class<T> componentClass) throws InstantiationException, IllegalAccessException, ReflectionException {
		return getMapper(componentClass).create(entityId);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> ComponentMapper<T> getMapper(Class<T> componentClass) {
		ComponentType type = getTypeFor(componentClass);
		return mappers.get(type.getIndex());
	}

	
	public ComponentType getTypeFor(Class<? extends Component> componentClass) {
		
		ComponentType type = typemap.get(componentClass);
		
		if (type == null)
			type = createType(componentClass);
		
		return type;
	}
	
	private ComponentType createType(Class<? extends Component> componentClass) {
		
		ComponentType type = new ComponentType(types.size(), componentClass);
		typemap.put(componentClass, type);
		types.add(type);
		
		createMapper(type);
		
		return type;
	}
	
	private void createMapper(ComponentType type) {
		
		int index = type.getIndex();
		ComponentMapper mapper = new ComponentMapper(type.getBase(), this);
		// Size mappers's contents
		mappers.set(index, mapper);
		
	}
	

	
}
