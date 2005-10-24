package de.berlios.imilarity.fuzzy;

import java.util.HashMap;
import java.util.Map;

public class HashedFuzzySet extends FuzzySetBase {

	private final Map memberships = new HashMap();
	private Integer elementsCount = null;
	
	public HashedFuzzySet(int elementsCount) {
		super();
		this.elementsCount = new Integer(elementsCount);
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

	public Membership getMembership(int element) {
		return (Membership) memberships.get(new Integer(element));
	}
	
	public void addMembership(Membership m) {
		memberships.put(new Integer(memberships.size()), m);
	}
	
	public void addMembership(int element, Membership m) {
		memberships.put(new Integer(element), m);
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
					new SimpleMembership(getMembership(i).and(set.getMembership(i)).abs()));
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		int elementsCount = getElementsCount();
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).or(set.getMembership(i)).abs()));
		return result;
	}

	public FuzzySet complement() {
		int elementsCount = getElementsCount();
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).complement().abs()));
		return result;
	}
}
