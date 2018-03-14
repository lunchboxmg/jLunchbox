package com.jlunchbox.core.ecs;

import java.util.IdentityHashMap;

import com.jlunchbox.core.util.Bag;

public class ComponentManager {
	
	// Container that links component classes with component type classes
	private final IdentityHashMap<Class<? extends Component>, ComponentType> typemap = 
			new IdentityHashMap<Class<? extends Component>, ComponentType>();

	// Container of created types
	private final Bag<ComponentType> types = new Bag<ComponentType>();
	
	private final Bag<ComponentMapper> mappers = new Bag<ComponentMapper>();

	public ComponentManager() {

	}
	
	public ComponentType getTypeFor(Class<? extends Component> componentClass) {
		
		ComponentType type = typemap.get(componentClass);
		
		if (type == null)
			type = create(componentClass);
		
		return type;
	}
	
	private ComponentType create(Class<? extends Component> componentClass) {
		
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
