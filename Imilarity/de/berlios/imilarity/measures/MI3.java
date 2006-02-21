package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class MI3 extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		//FuzzySet ac = a.complement();
		//FuzzySet bc = b.complement();
		//double d = a.union(b).intersection(ac.union(bc)).abs();
		double d = a.uiu(b).abs();
		if (d == 0)
			return 0;
		else
			//return a.intersection(b).intersection(ac.intersection(bc)).abs() / d;
			return a.iii(b).abs() / d;
	}

	public String getDescription() {
		return "MI3";
	}

}
