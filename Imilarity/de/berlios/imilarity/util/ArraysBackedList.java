/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ArraysBackedList implements List {

	private Object[][] arrays;
	private int totalSize = 0;
	private int individualSize = 0;
	
	public ArraysBackedList(Object[][] arrays, int size) {
		individualSize = size;
		totalSize = arrays.length * size;
		this.arrays = arrays;
	}
	
	public int size() {
		return totalSize;
	}

	public boolean isEmpty() {
		return totalSize > 0;
	}

	public boolean contains(Object o) {
		for (int i = 0; i < arrays.length; i++) {  
			if (arrays[i].equals(o))
				return true;
		}
		return false;
	}

	public Iterator iterator() {
		return listIterator();
	}

	public Object[] toArray() {
		Object[] result = new Object[totalSize];
		Iterator it = iterator();
		for (int i = 0; i < result.length && it.hasNext(); i++)
			result[i] = it.next();
		return result;
	}

	public Object[] toArray(Object[] a) {
		a = (Object[])java.lang.reflect.Array.
			newInstance(a.getClass().getComponentType(), totalSize);
		System.arraycopy(toArray(), 0, a, 0, totalSize);
		return a;
	}

	public boolean add(Object arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection arg0) {
		Iterator it = iterator();
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
		int i1 = index / individualSize;
		int i2 = index % individualSize;
		if (arrays[i1] == null)
			return null;
		return arrays[i1][i2];
	}

	public Object set(int index, Object arg1) {
		int i1 = index / individualSize;
		int i2 = index % individualSize;
		if (arrays[i1] == null)
			return null;
		Object prev = arrays[i1][i2];
		arrays[i1][i2] = arg1;
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
		Iterator it = iterator();
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
		Iterator it = iterator();
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
		ArraysBackedList l = new ArraysBackedList(new Integer[][] {a1, a2, a3}, 3);
		l.set(3, new Integer(11));
		Iterator it = l.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
	
}
