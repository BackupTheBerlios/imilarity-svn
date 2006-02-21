package de.berlios.imilarity.util;

import java.util.Iterator;

/**
 * Combineert twee geordende iterators (elementen die overlopen worden 
 * zijn Comparable's en ze komen van klein naar groot aan bod) tot 1 geordende
 * iterator.
 * 
 * @author Klaas Bosteels
 * @since 21-feb-2006
 */
public class CombinedIterator implements Iterator {

	private Iterator it1, it2;
	private Comparable buf1 = null, buf2 = null;
	
	public CombinedIterator(Iterator it1, Iterator it2) {
		this.it1 = it1;
		this.it2 = it2;
	}
	
	public boolean hasNext() {
		return buf1 != null || buf2 != null || it1.hasNext() || it2.hasNext();
	}

	public Object next() {
		Object ret = null;
		if (buf1 == null && it1.hasNext())
			buf1 = (Comparable) it1.next();
		if (buf2 == null && it2.hasNext())
			buf2 = (Comparable) it2.next();
		if (buf1 != null && buf2 != null) {
			if (buf1.compareTo(buf2) == 0) { 
				ret = buf1;
				buf1 = null;
				buf2 = null;
			} else if (buf1.compareTo(buf2) < 0) {
				ret = buf1;
				buf1 = null;
			} else {
				ret = buf2;
				buf2 = null;
			}
		} else {
			if (buf1 != null) {
				ret = buf1;
				buf1 = null;
			} else {
				ret = buf2;
				buf2 = null;
			}
		}
		return ret;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	
	
	// TESTPROGRAMMA
	
	public static void main(String[] args) {
		ArrayIterator it1 = new ArrayIterator(new Integer[] { 
			new Integer(1), new Integer(2), new Integer(4), new Integer(5), new Integer(7), new Integer(9)
		});
		ArrayIterator it2 = new ArrayIterator(new Integer[] {
			new Integer(2), new Integer(3), new Integer(8), new Integer(10), new Integer(12) 	
		});

		Iterator it = new CombinedIterator(it1,it2);
		while (it.hasNext()) {
			int i = ((Integer) it.next()).intValue();
			System.out.print("" + i + " ");
		}
		System.out.println();
	}
}
