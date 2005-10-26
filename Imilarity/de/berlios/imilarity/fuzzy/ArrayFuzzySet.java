package de.berlios.imilarity.fuzzy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayFuzzySet extends FuzzySetBase {

	private List memberships;
	
	public ArrayFuzzySet() {
		super();
		memberships = new ArrayList();
	}
	
	public ArrayFuzzySet(Collection c) {
		this();
		memberships.addAll(c);
	}
	
	
	public int getElementsCount() {
		return memberships.size();
	}

	public Membership getMembership(int element) {
		return (Membership) memberships.get(element);
	}
	
	public void addMembership(Membership m) {
		memberships.add(m);
	}
	
	public Membership changeMembership(int element, Membership m) {
		return (Membership) memberships.set(element, m);
	}
	
	public void sort(Comparator comparator) {
		Collections.sort(memberships, comparator);
	}

	public ArrayFuzzySet head(int elementCount) {
		return new ArrayFuzzySet(memberships.subList(0,elementCount));
	}
	
	
	public FuzzySet intersection(FuzzySet set) {
		if (set.getElementsCount() != memberships.size())
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet();
		Iterator it = memberships.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Membership m = (Membership) it.next();
			result.addMembership(new SimpleMembership(m.and(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		if (set.getElementsCount() != memberships.size())
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet();
		Iterator it = memberships.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Membership m = (Membership) it.next();
			result.addMembership(new SimpleMembership(m.or(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet complement() {
		ArrayFuzzySet result = new ArrayFuzzySet();
		Iterator it = memberships.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Membership m = (Membership) it.next();
			result.addMembership(new SimpleMembership(m.complement().getComponents()));
		}
		return result;
	}

}
