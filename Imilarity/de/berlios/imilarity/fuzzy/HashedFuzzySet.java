package de.berlios.imilarity.fuzzy;

import java.util.HashMap;
import java.util.Map;

public class HashedFuzzySet implements FuzzySet {

	private final Map memberships = new HashMap();
	
	public int getElementsCount() {
		return memberships.size();
	}

	public Membership getMembership(int element) {
		return (Membership) memberships.get(new Integer(element));
	}
	
	public void addMembership(Membership m) {
		memberships.put(new Integer(memberships.size()), m);
	}
	
	public Membership changeMembership(int element, Membership m) {
		return (Membership) memberships.put(new Integer(element), m);
	}
}
