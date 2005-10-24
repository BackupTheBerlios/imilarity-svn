package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M9 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		return Math.min(a.abs(),b.abs()) / a.union(b).abs();
	}

	public String getDescription() {
		return "M9";
	}

}
