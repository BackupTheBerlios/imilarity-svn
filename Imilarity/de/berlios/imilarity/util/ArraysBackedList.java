/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArraysBackedList implements List {

	private List arrays = new ArrayList();
	private int totalSize = 0;
	private int individualSize = 0;
	
	public ArraysBackedList(int size) {
		individualSize = size;
	}
	
	public void addArray(Object[] array) {
		if (array == null)
			throw new NullPointerException("array == null");
		if (array.length != individualSize)
			throw new IllegalArgumentException("length of array must be " + individualSize);
		arrays.add(array);
		totalSize += array.length;
	}
	
	public void removeArray(Object[] array) {
		if (array == null)
			throw new NullPointerException("array == null");
		if (!arrays.contains(array))
			throw new IllegalArgumentException("does not contain array");
		arrays.remove(array);
		totalSize -= array.length;
	}
	
	public int size() {
		return totalSize;
	}

	public boolean isEmpty() {
		return totalSize > 0;
	}

	public boolean contains(Object o) {
		Iterator it = arrays.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (obj.equals(o))
				return true;
		}
		return false;
	}

	public Iterator iterator() {
		return listIterator();
	}

	public Object[] toArray() {
		Object[] result = new Object[totalSize];
		Iterator it = arrays.iterator();
		for (int i = 0; i < result.length && it.hasNext(); i++)
			result[i] = it.next();
		return result;
	}

	public Object[] toArray(Object[] arg0) {
		return Arrays.copyOf(toArray(), totalSize, arg0.getClass());
	}

	public boolean add(Object arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection arg0) {
		Iterator it = arg0.iterator();
		while (it.hasNext())
			if (!contains(it.next()))
				return false;
		return true;
	}

	public boolean addAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(int arg0, Collection arg1) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public Object get(int index) {
		return ((Object[])arrays.get(index / individualSize))[index % individualSize];
	}

	public Object set(int arg0, Object arg1) {
		Object prev = ((Object[])arrays.get(arg0 / individualSize))[arg0 % individualSize];
		((Object[])arrays.get(arg0 / individualSize))[arg0 % individualSize] = arg1;
		return prev;
	}

	public void add(int arg0, Object arg1) {
		throw new UnsupportedOperationException();
	}

	public Object remove(int index) {
		throw new UnsupportedOperationException();
	}

	public int indexOf(Object o) {
		int index = 0;
		Iterator it = arrays.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (obj.equals(o))
				break;
			index++;
		}
		return index;
	}

	public int lastIndexOf(Object o) {
		int index = 0, i = 0;
		Iterator it = arrays.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (obj.equals(o))
				index = i;
			i++;
		}
		return index;
	}

	public ListIterator listIterator() {
		return new MyIterator();
	}

	public ListIterator listIterator(int index) {
		return new MyIterator(index);
	}

	public List subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	
	private class MyIterator implements ListIterator {

		private int index = 0, prevIndex = 0;
		
		public MyIterator(int i) {
			index = i;
		}
		
		public MyIterator() {
			this(0);
		}
		
		public boolean hasNext() {
			return index < totalSize;
		}
		
		public Object next() {
			prevIndex = index++;
			return get(prevIndex);
		}
		
		public boolean hasPrevious() {
			return index > 0;
		}
		
		public Object previous() {
			prevIndex = --index;
			return ArraysBackedList.this.get(prevIndex);
		}
		
		public int nextIndex() {
			return index;
		}
		
		public int previousIndex() {
			return index-1;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public void set(Object arg0) {
			ArraysBackedList.this.set(prevIndex, arg0);
		}
		
		public void add(Object arg0) {
			throw new UnsupportedOperationException();
		}
	}
	
	
	// testprogramma
	public static void main(String[] args) {
		Integer[] 
		    a1 = { 
				new Integer(1), new Integer(2), new Integer(3)
			}, 
			a2 = {
				new Integer(4), new Integer(5), new Integer(6)
			}, 
			a3 = {
				new Integer(7), new Integer(8), new Integer(9)
			};
		ArraysBackedList l = new ArraysBackedList(3);
		l.addArray(a1);
		l.addArray(a2);
		l.addArray(a3);
		l.set(3, new Integer(11));
		Iterator it = l.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}
