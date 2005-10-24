package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M7 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		return a.intersection(b).abs() / Math.max(a.abs(),b.abs());
	}

	public String getDescription() {
		return "M7";
	}

}
