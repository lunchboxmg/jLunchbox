package com.jlunchbox.test;

import java.util.Random;

import com.jlunchbox.core.ecs.Component;
import com.jlunchbox.core.ecs.ComponentManager;
import com.jlunchbox.core.ecs.ComponentMapper;
import com.jlunchbox.core.ecs.ComponentType;
import com.jlunchbox.core.util.Bag;

/** 
 * This class is a basic test of my initial plan on how to structure
 * components.  An example of what I want is ...
 * 
 * Entity A = Health based on 2d10+4
 * Entity B = Health based on 3d6+5
 * 
 * If a new Entity A is created, it will use to blueprint that dictates
 * how the roll will occur.
 * 
 * @author lunchboxmg
 *
 */
public class ComponentTest {

	public ComponentTest() { }

	public boolean test() {
		
		ComponentManager cm = new ComponentManager();
		try {
			TestHealthComponent tc = cm.create(0, TestHealthComponent.class);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		TestHealthComponentLoader loader = new TestHealthComponentLoader();
		
		String line = "HP\t2\t10\t4"; // 2d10+4
		TestHealthComponentBlueprint blueSuits = loader.loadBlueprint(line);
		
		line = "HP\t3\t6\t5"; // 3d6+5
		TestHealthComponentBlueprint greySuits = loader.loadBlueprint(line);
		
		Bag<TestHealthComponent> components = new Bag<TestHealthComponent>(10);
		
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int choice = rand.nextInt(1);
			if (choice == 1)
				components.add(blueSuits.createComponent());
			else
				components.add(greySuits.createComponent());
		}
		
		for(int i = 0; i < components.size(); i++) {
			TestHealthComponent item = components.get(i); 
			System.out.println(item.max);
		}
		
		return true;
	}


	public class TestHealthComponent extends Component {
		
		public int current;
		public int max;
		
		/** 
		 * Base constructor for Health Component.  Health will be set at
		 * the max value
		 * @param max
		 */
		
		public TestHealthComponent() {
			current = 0;
			max = 0;
		}
		
		public TestHealthComponent(int max) {
			current = max;
			this.max = max;
		}
		
		/**
		 * Used to generate a Health Component from a saved state
		 * @param current
		 * @param max
		 */
		public TestHealthComponent(int current, int max) {
			this.current = current;
			this.max = max;
		}
		
	}

	public class TestHealthComponentLoader {
		
		public TestHealthComponentLoader () { }

		public TestHealthComponentBlueprint loadBlueprint(String line) {
			
			String[] parts = line.split("\t");
			
			int num = Integer.parseInt(parts[1]);
			int size = Integer.parseInt(parts[2]);
			int base = Integer.parseInt(parts[3]);
			
			return new TestHealthComponentBlueprint(num, size, base);
		}
		
	}
	
	public class TestHealthComponentBlueprint {
		
		private int num;
		private int size;
		private int base;
		
		public TestHealthComponentBlueprint(int num, int size, int base) {
			
			this.num = num;
			this.size = size;
			this.base = base;
		}
		
		public TestHealthComponent createComponent() {
			
			Random rand = new Random();
			
			int max = 0;
			for (int i = 0; i < num; i++)
				max += rand.nextInt(size) + 1;
			max += base;
			
			return new TestHealthComponent(max);
		}
	}
}
