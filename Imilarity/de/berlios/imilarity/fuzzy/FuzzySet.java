package de.berlios.imilarity.fuzzy;

public interface FuzzySet {

	public int getElementsCount();
	
	public Membership getMembership(int element);
	
}
