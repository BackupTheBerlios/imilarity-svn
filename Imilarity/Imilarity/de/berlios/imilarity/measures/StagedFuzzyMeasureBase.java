package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public abstract class StagedFuzzyMeasureBase extends FuzzyMeasureBase implements StagedFuzzyMeasure {
	
	public double getSimilarity() {
		FuzzySet query = getQuery();
		FuzzySet target = getTarget();
		if (query == null || target == null || 
			query.getElementsCount() != target.getElementsCount())
			return 0.0;
		int pc = query.getElementsCount();
		for (int i = 0; i < pc; i++)
			compare(i);
		double result = combine();
		reset();
		return result;
	}
	
}
