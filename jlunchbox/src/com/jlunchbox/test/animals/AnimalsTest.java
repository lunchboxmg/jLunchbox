package com.jlunchbox.test.animals;

import java.util.Random;

import com.jlunchbox.core.ecs.ComponentManager;
import com.jlunchbox.core.ecs.EntityType;
import com.jlunchbox.core.ecs.World;
import com.jlunchbox.core.util.ReflectionException;

public class AnimalsTest {
	
	private World world;
	static Random rand = new Random();

	public AnimalsTest() {
		
		world = new World(10);
		loadComponents();
	}
	
	public void test() {
		
		ComponentManager cm = world.getComponentManager();

		HealthBlueprint health = new HealthBlueprint(cm.getTypeFor(HealthComponent.class), 2, 10, 4);
		HealthComponent c1 = health.create();
		System.out.println(c1.current + "/" + c1.max);
		
		EntityType dog = new EntityType();
		dog.addComponent(cm.getTypeFor(HealthComponent.class), health);
		HealthComponent c2 = (HealthComponent) dog.create();
		System.out.println(c2.current + "/" + c2.max);
		
		try {
			cm.create(0, HealthComponent.class);
		} catch (InstantiationException | IllegalAccessException | ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadComponents() {

		ComponentManager cm = world.getComponentManager();
		cm.getMapper(NameComponent.class);
		cm.getMapper(HealthComponent.class);
		cm.getMapper(PositionComponent.class);
		System.out.println(cm.getTypeFor(PositionComponent.class).getIndex());
	}
	
	static int getRandom(int num) {
		return rand.nextInt(num);
	}
	
}
