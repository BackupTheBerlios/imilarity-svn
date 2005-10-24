package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M6 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		return a.intersection(b).abs() / a.union(b).abs();
	}

	public String getDescription() {
		return "M6";
	}

}
