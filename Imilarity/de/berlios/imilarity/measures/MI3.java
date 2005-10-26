package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class MI3 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		double d = a.union(b).intersection(a.complement().union(b.complement())).abs();
		if (d == 0)
			return 0;
		else
			return a.intersection(b).intersection
				(a.complement().intersection(b.complement())).abs() / d; 
	}

	public String getDescription() {
		return "MI3";
	}

}
