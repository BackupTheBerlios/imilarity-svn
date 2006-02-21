package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M10c extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		int count = a.getElementsCount();
		double d = count - a.intersection(b).abs();
		if (d == 0)
			return 0;
		else
			return (count - Math.min(a.abs(),b.abs())) / d;
	}

	public String getDescription() {
		return "M10c";
	}

}
