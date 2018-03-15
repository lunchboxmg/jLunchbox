package com.jlunchbox.core.ecs;

import com.jlunchbox.core.util.Bag;
import com.jlunchbox.core.util.ClassReflection;
import com.jlunchbox.core.util.ReflectionException;

public class ComponentMapper<A extends Component> {
	
	final ComponentType type;
	final Bag<A> components;

	public ComponentMapper(Class<A> componentClass, ComponentManager cm) {
		this.type = cm.getTypeFor(componentClass); // TODO: This seems convoluted
		components = new Bag<A>(componentClass);
	}
	
<<<<<<< HEAD
	@SuppressWarnings("unchecked")
	public A create(int entityId) throws InstantiationException, IllegalAccessException {
		A component = get(entityId);
		if (component == null) {
			component = (A) type.getBase().newInstance();
=======
	public A create(int entityId) throws InstantiationException, IllegalAccessException, ReflectionException {
		A component = get(entityId);
		if (component == null) {
			component = createNew();
>>>>>>> b7d253410d5325e14a828bfda37d56b3f73b4e99
			components.set(entityId, component);
		}
		return component;
	}
<<<<<<< HEAD
=======

	@SuppressWarnings("unchecked")
	private A createNew() throws InstantiationException, IllegalAccessException, ReflectionException { 
		return (A) ClassReflection.newInstance(type.getBase());
	}
>>>>>>> b7d253410d5325e14a828bfda37d56b3f73b4e99
	
	public A get(int entityId) throws ArrayIndexOutOfBoundsException {
		return components.get(entityId);
	}
	

}
