package de.berlios.imilarity.fuzzy;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayFuzzySet extends FuzzySetBase {

	private Membership[] memberships;
	private int elementsCount = 0;
	private Membership defaultMembership = null;
	
	
	private ArrayFuzzySet(Membership[] memberships, int elementsCount, Membership defaultMembership) {
		super();
		this.memberships = memberships;
		this.elementsCount = elementsCount;
		this.defaultMembership = defaultMembership;
	}
	
	public ArrayFuzzySet(int elementsCount, Membership defaultMembership) {
		this(new Membership[elementsCount], elementsCount, defaultMembership);
	}
	
	public ArrayFuzzySet(int elementsCount) {
		this(elementsCount, new SimpleMembership(0));
	}
	
	//public ArrayFuzzySet(Collection c) {
	//	this();
	//	memberships.addAll(c);
	//}
	
	
	public int getElementsCount() {
		return memberships.length;
	}

	public Membership getMembership(int element) {
		if (element < 0 || element >= elementsCount)
			throw new IndexOutOfBoundsException();
		Membership res = memberships[element];
		if (res == null)
			return defaultMembership;
		return res;
	}
	
	//public void addMembership(Membership m) {
	//	memberships.add(m);
	//}
	
	public void addMembership(int element, Membership m) {
		memberships[element] = m;
	}
	
	public Membership changeMembership(int element, Membership m) {
		Membership old = memberships[element];
		memberships[element] = m;
		return old;
	}
	
	public void sort(Comparator comparator) {
		Arrays.sort(memberships, comparator);
	}

	public ArrayFuzzySet head(int elementCount) {
		return new ArrayFuzzySet(memberships, elementCount, defaultMembership);
	}
	
	
//	public FuzzySet intersection(FuzzySet set) {
//		if (set.getElementsCount() != memberships.size())
//			throw new IllegalArgumentException("not the same elements count");
//		ArrayFuzzySet result = new ArrayFuzzySet();
//		Iterator it = memberships.iterator();
//		for (int i = 0; it.hasNext(); i++) {
//			Membership m = (Membership) it.next();
//			result.addMembership(new SimpleMembership(m.and(set.getMembership(i)).getComponents()));
//		}
//		return result;
//	}
//
//	public FuzzySet union(FuzzySet set) {
//		if (set.getElementsCount() != memberships.size())
//			throw new IllegalArgumentException("not the same elements count");
//		ArrayFuzzySet result = new ArrayFuzzySet();
//		Iterator it = memberships.iterator();
//		for (int i = 0; it.hasNext(); i++) {
//			Membership m = (Membership) it.next();
//			result.addMembership(new SimpleMembership(m.or(set.getMembership(i)).getComponents()));
//		}
//		return result;
//	}
//
//	public FuzzySet complement() {
//		ArrayFuzzySet result = new ArrayFuzzySet();
//		Iterator it = memberships.iterator();
//		for (int i = 0; it.hasNext(); i++) {
//			Membership m = (Membership) it.next();
//			result.addMembership(new SimpleMembership(m.complement().getComponents()));
//		}
//		return result;
//	}

}
