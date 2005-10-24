package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M8 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		return a.intersection(b).abs() / Math.min(a.abs(),b.abs());
	}

	public String getDescription() {
		return "M8";
	}

}
