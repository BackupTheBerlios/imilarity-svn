package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class MI3 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		return a.intersection(b).intersection(a.complement().intersection(b.complement())).abs() 
			/ a.union(b).intersection(a.complement().union(b.complement())).abs();
	}

	public String getDescription() {
		return "MI3";
	}

}
