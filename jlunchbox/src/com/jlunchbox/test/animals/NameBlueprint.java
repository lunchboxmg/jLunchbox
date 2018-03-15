package com.jlunchbox.test.animals;

public class NameBlueprint {
	
	static final String[] names = {"Bob", "Tom", "Alice", "Jimmy", "Martin", "Jennifer"};

	public NameBlueprint() { }
	
	public NameComponent create() {

		int which = AnimalsTest.getRandom(names.length);
		NameComponent out = new NameComponent();
		out.name = names[which];
		return out;
	}

}
