package com.jlunchbox.core.ecs;

public class ComponentType {

	private final int index; // Used for fast lookup .. becomes hashcode
	private final Class<? extends Component> base; // class type

	public ComponentType(int index, Class<? extends Component> base) {
		this.index = index;
		this.base = base;
	}
	
	@Override
	public boolean equals(Object other) {

		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		ComponentType casted = (ComponentType) other;
		return index == casted.index;
	}
	
	@Override
	public int hashCode() { return index; }
	
	// GETTERS
	
	public int getIndex() { return index; }
	public Class<? extends Component> getBase() { return base; }

}
