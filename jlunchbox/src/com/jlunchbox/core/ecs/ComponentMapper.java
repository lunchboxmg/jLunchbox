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
	
	public A create(int entityId) throws InstantiationException, IllegalAccessException, ReflectionException {
		A component = get(entityId);
		if (component == null) {
			component = createNew();
			components.set(entityId, component);
		}
		return component;
	}

	@SuppressWarnings("unchecked")
	private A createNew() throws InstantiationException, IllegalAccessException, ReflectionException { 
		return (A) ClassReflection.newInstance(type.getBase());
	}
	
	public A get(int entityId) throws ArrayIndexOutOfBoundsException {
		return components.get(entityId);
	}
	

}
