package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class MI3c extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		return a.intersection(b).union(a.complement().intersection(b.complement())).abs() 
			/ a.union(b).union(a.complement().union(b.complement())).abs();
	}

	public String getDescription() {
		return "MI3c";
	}

}
