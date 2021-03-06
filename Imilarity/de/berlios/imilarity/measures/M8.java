package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M8 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		double d = Math.min(a.abs(),b.abs());
		if (d == 0)
			return 0;
		else
			return a.intersection(b).abs() / d; 
	}

	public String getDescription() {
		return "M8";
	}

}
