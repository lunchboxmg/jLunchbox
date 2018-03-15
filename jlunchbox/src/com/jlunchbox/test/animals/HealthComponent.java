package com.jlunchbox.test.animals;

import com.jlunchbox.core.ecs.Component;

public class HealthComponent extends Component {
	
	public int current;
	public int max;

	public HealthComponent() {
		// TODO Auto-generated constructor stub
	}
	
	public HealthComponent(int max) {
		current = max;
		this.max = max;
	}

}
