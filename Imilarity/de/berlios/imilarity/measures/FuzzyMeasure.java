package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public interface FuzzyMeasure {
	
	/**
	 * @param query set waarmee de doelsets vergeleken moeten worden
	 */
	public void setQuery(FuzzySet query);
	public FuzzySet getQuery();
	
	/**
	 * @param target de huidige doelset
	 */
	public void setTarget(FuzzySet target);
	public FuzzySet getTarget();
	
	
	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen de query- en target-set weergeeft.
	 */
	public double getSimilarity();
	
	
	public String getDescription();
}
