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
	
<<<<<<< HEAD
	private final Bag<ComponentMapper> mappers = new Bag(ComponentMapper.class);
=======
	@SuppressWarnings("rawtypes")
	private final Bag<ComponentMapper> mappers = new Bag<ComponentMapper>();
>>>>>>> b7d253410d5325e14a828bfda37d56b3f73b4e99

	public ComponentManager() {

	}
	
<<<<<<< HEAD
	public <T extends Component> T create(int entityId, Class<T> componentClass) throws InstantiationException, IllegalAccessException {
		return getMapper(componentClass).create(entityId);
	}
	
	protected <T extends Component> ComponentMapper<T> getMapper(Class<T> componentClass) {
		return mappers.get(getTypeFor(componentClass).getIndex());
	}
=======
	public <T extends Component> T create(int entityId, Class<T> componentClass) throws InstantiationException, IllegalAccessException, ReflectionException {
		return getMapper(componentClass).create(entityId);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> ComponentMapper<T> getMapper(Class<T> componentClass) {
		ComponentType type = getTypeFor(componentClass);
		return mappers.get(type.getIndex());
	}

>>>>>>> b7d253410d5325e14a828bfda37d56b3f73b4e99
	
	public ComponentType getTypeFor(Class<? extends Component> componentClass) {
		
		ComponentType type = typemap.get(componentClass);
		
		if (type == null)
<<<<<<< HEAD
			type = createComponentType(componentClass);
=======
			type = createType(componentClass);
>>>>>>> b7d253410d5325e14a828bfda37d56b3f73b4e99
		
		return type;
	}
	
<<<<<<< HEAD
	public ComponentType createComponentType(Class<? extends Component> componentClass) {
=======
	private ComponentType createType(Class<? extends Component> componentClass) {
>>>>>>> b7d253410d5325e14a828bfda37d56b3f73b4e99
		
		ComponentType type = new ComponentType(types.size(), componentClass);
		typemap.put(componentClass, type);
		types.add(type);
		
		createMapper(type);
		
		return type;
	}
	
	private void createMapper(ComponentType type) {
		
		int index = type.getIndex();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ComponentMapper mapper = new ComponentMapper(type.getBase(), this);
		// Size mappers's contents
		mappers.set(index, mapper);
		
	}
	

	
}
