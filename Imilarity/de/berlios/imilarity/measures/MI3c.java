package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class MI3c extends FuzzyMeasureBase {

	public double getSimilarity() {
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		//FuzzySet ac = a.complement();
		//FuzzySet bc = b.complement();
		int count = a.getElementsCount();
		//double d = count - a.intersection(b).intersection(ac.intersection(bc)).abs();
		double d = count - a.iii(b).abs();
		if (d == 0)
			return 0;
		else
			//return  (count - a.union(b).intersection(ac.union(bc)).abs())/ d;
			return (count - a.uiu(b).abs()) / d;
	}

	public String getDescription() {
		return "MI3c";
	}

}
