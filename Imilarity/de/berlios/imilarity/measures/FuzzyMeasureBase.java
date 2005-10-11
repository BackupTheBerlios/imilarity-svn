package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;


public abstract class FuzzyMeasureBase implements FuzzyMeasure {
	
	private FuzzySet query, target;
	
	public void setQuery(FuzzySet query) {
		if (query == null)
			throw new NullPointerException("query == null");
		this.query = query;
	}
	
	public FuzzySet getQuery() {
		return query;
	}
	
	public void setTarget(FuzzySet target) {
		if (target == null)
			throw new NullPointerException("target == null");
		this.target = target;
	}
	
	public FuzzySet getTarget() {
		return target;
	}
	
	
	public String toString() {
		return getDescription();
	}

}
