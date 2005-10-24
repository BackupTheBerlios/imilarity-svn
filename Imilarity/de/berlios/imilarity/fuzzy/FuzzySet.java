package de.berlios.imilarity.fuzzy;

public interface FuzzySet {

	public int getElementsCount();
	
	public Membership getMembership(int element);

	
	public FuzzySet intersection(FuzzySet set);
	
	public FuzzySet union(FuzzySet set);
	
	public FuzzySet complement();
	
	public double abs();
	
}
