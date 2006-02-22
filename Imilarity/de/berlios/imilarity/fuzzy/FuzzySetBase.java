package de.berlios.imilarity.fuzzy;

import java.util.Iterator;

public abstract class FuzzySetBase implements FuzzySet {

	public Iterator iterator() {
		return new MyIterator();
	}
	
	public double abs() {
		if (getElementsCount() == 0)
			return 0.0;
		else {
			double sum = 0.0;
			Iterator it = iterator();
			//for (int i = 0; i < getElementsCount(); i++) {
			while (it.hasNext()) {
				int i = ((Integer)it.next()).intValue();
				double part = 0.0;
				double[] components = getMembership(i).getComponents();
				for (int j = 0; j < components.length; j++)
					part += components[j]*components[j];
				sum += Math.sqrt(part);
			}
			return sum / Math.sqrt(getMembership(0).getComponents().length);
		}
	}
	
	public FuzzySet intersection(FuzzySet set) {
		int count = getElementsCount();
		if (set.getElementsCount() != count)
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet(count, new SimpleMembership(0));
		Iterator it = iterator();
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
		//for (int i = 0; i < count; i++) {
			Membership m = getMembership(i);
			result.addMembership(i, new SimpleMembership(m.and(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		int count = getElementsCount();
		if (set.getElementsCount() != count)
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet(count, new SimpleMembership(0));
		//for (int i = 0; i < count; i++) {
		Iterator it = iterator();
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			Membership m = getMembership(i);
			result.addMembership(i, new SimpleMembership(m.or(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet complement() {
		int count = getElementsCount();
		ArrayFuzzySet result = new ArrayFuzzySet(count);
		for (int i = 0; i < count; i++) {
			Membership m = getMembership(i);
			result.addMembership(i, new SimpleMembership(m.complement().getComponents()));
		}
		return result;
	}
	
	public FuzzySet minus(FuzzySet set) {
		int count = getElementsCount();
		ArrayFuzzySet result = new ArrayFuzzySet(count, new SimpleMembership(0));
		Iterator it = iterator();
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).minus(set.getMembership(i)).getComponents()));
		}
		return result;
	}
	
	public FuzzySet iii(FuzzySet set) {
		int count = getElementsCount();
		ArrayFuzzySet result = new ArrayFuzzySet(count, new SimpleMembership(0));
		Iterator it = iterator();
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			Membership m1 = getMembership(i);
			Membership m2 = set.getMembership(i);
			result.addMembership(i, 
					new SimpleMembership(m1.and(m2).and(m1.complement().and(m2.complement()))
							.getComponents()));
		}
		return result;
	}
	
	public FuzzySet uiu(FuzzySet set) {
		int count = getElementsCount();
		ArrayFuzzySet result = new ArrayFuzzySet(count, new SimpleMembership(0));
		Iterator it = iterator();
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			Membership m1 = getMembership(i);
			Membership m2 = set.getMembership(i);
			result.addMembership(i, 
					new SimpleMembership(m1.or(m2).and(m1.complement().or(m2.complement()))
							.getComponents()));
		}
		return result;
	}
	
	
	
	private class MyIterator implements Iterator {

		private int i = 0;
		
		public boolean hasNext() {
			return i < getElementsCount();
		}

		public Object next() {
			return new Integer(i++);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
