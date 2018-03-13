/*******************************************************************************
 * IMPORTANT: A vast majority of this file is a rewrite of Bag class in 
 * artemis-odb.  I rewrote it to make sure that I get a good grasp of its 
 * functionality and simply practice with coding.
 ******************************************************************************/

package com.jlunchbox.core.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Collection type a bit like ArrayList but does not preserve the order of its
 * entities, speedwise it is very good, especially suited for games.
 *
 * @param <E>
 *		object type this bag holds
 *
 * @author Arni Arent
 */
public class Bag<E> {
	
	E[] data;
	
	protected int size = 0;
	
	public Bag() { this(64); }
	
	public Bag(Class<E> type) { this(type, 64); }
	
	@SuppressWarnings("unchecked")
	public Bag(int capacity) {
		data = (E[]) Array.newInstance(Object.class, capacity);
	}
	
	@SuppressWarnings("unchecked")
	public Bag(Class<E> type, int capacity) {
		data = (E[]) Array.newInstance(type, capacity);
	}
	
	/* The following functions treat the bag as an expanded bag in which
	 * items are added one after the other with no "empty" space in between.
	 * i.e.: like an array
	 */
	
	public void add(E e) {
		if (size == data.length)
			grow(data.length * 2); // TODO: May need optimized
		data[size++] = e;
	}
	
	public boolean remove(E e) {
		for (int i = 0; i < size; i++) {
			E other = data[i];
			
			if (e.equals(other)) {
				data[i] = data[--size]; // replace input item with last item
				data[size] = null; // mark the last item as nothing
				return true;
			}
		}
		return false; // item E didn't exist in the bag
	}
	
	// Utility Functions
	
	/** Internal function to expand the array while preserving data
	 * @param newCapacity how many elements the Bag will hold now
	 */
	private void grow(int newCapacity) throws ArrayIndexOutOfBoundsException {
		data = Arrays.copyOf(data, newCapacity);
	}
	
	public int size() { return size; }
	
	public int capacity() { return data.length; }
	
	
}
