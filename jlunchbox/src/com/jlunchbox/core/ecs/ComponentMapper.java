package com.jlunchbox.core.ecs;

import com.jlunchbox.core.util.Bag;

public class ComponentMapper<A extends Component> {
	
	final ComponentType type;
	final Bag<A> components;

	public ComponentMapper(Class<A> componentClass, ComponentManager cm) {
		this.type = cm.getTypeFor(componentClass); // TODO: This seems convoluted
		components = new Bag<A>(componentClass);
	}
	
	public A get(int entityId) throws ArrayIndexOutOfBoundsException {
		return components.get(entityId);
	}

}
