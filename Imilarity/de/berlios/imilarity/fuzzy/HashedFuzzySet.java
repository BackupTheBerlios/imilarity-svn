package de.berlios.imilarity.fuzzy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class HashedFuzzySet extends FuzzySetBase {

	private final Map memberships = new HashMap();
	private Integer elementsCount = null;
	private Membership defaultMembership = null;
	
	private SortedSet elements = new TreeSet();
	
	public HashedFuzzySet(int elementsCount, Membership defaultMembership) {
		super();
		this.elementsCount = new Integer(elementsCount);
		this.defaultMembership = defaultMembership;
	}
	
	public HashedFuzzySet() {
		super();
	}
	
	
	public int getElementsCount() {
		if (elementsCount != null)
			return elementsCount.intValue();
		else
			return memberships.size();
	}
	
	public Iterator iterator() {
		return elements.iterator();
	}

	public Membership getMembership(int element) {
		Membership res = (Membership) memberships.get(new Integer(element));
		if (res == null)
			return defaultMembership;
		return res;
	}
	
	public void addMembership(Membership m) {
		addMembership(memberships.size(), m);
	}
	
	public void addMembership(int element, Membership m) {
		Integer el = new Integer(element);
		elements.add(el);
		memberships.put(el, m);
	}
	
	public Membership changeMembership(int element, Membership m) {
		return (Membership) memberships.put(new Integer(element), m);
	}

	public FuzzySet intersection(FuzzySet set) {
		int elementsCount = getElementsCount();
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).and(set.getMembership(i)).getComponents()));
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		int elementsCount = getElementsCount();
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).or(set.getMembership(i)).getComponents()));
		return result;
	}

	public FuzzySet complement() {
		int elementsCount = getElementsCount();
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).complement().getComponents()));
		return result;
	}
}
