package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M10 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		double d = a.union(b).abs();
		if (d == 0)
			return 0;
		else
			return Math.max(a.abs(),b.abs()) / d;
	}

	public String getDescription() {
		return "M10";
	}

}
