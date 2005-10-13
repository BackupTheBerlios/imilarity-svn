package de.berlios.imilarity.fuzzy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayFuzzySet implements FuzzySet {

	private List memberships = new ArrayList();
	
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

}
