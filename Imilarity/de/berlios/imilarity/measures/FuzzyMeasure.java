package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public interface FuzzyMeasure extends Measure {
	
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

}
