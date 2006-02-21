package de.berlios.imilarity.util;

import java.util.Iterator;

public class ArrayIterator implements Iterator {

	private Object[] array;
	private int i = 0;
	
	public ArrayIterator(Object[] array) {
		if (array == null)
			throw new NullPointerException("array == null");
		this.array = array;
	}
	
	public boolean hasNext() {
		return i < array.length;
	}

	public Object next() {
		return array[i++];
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
