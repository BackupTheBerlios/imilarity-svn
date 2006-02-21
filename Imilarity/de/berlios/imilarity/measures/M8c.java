package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M8c extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		int count = a.getElementsCount();
		double d = count - Math.max(a.abs(),b.abs());
		if (d == 0)
			return 0;
		else
			return (count - a.union(b).abs()) / d; 
	}

	public String getDescription() {
		return "M8c";
	}

}
