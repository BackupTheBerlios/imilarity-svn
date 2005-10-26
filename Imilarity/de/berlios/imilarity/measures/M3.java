package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.Membership;


public class M3 extends FuzzyMeasureBase {
	
	public double getSimilarity() {
		double sum = 0.0;
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		int count = a.getElementsCount();
		for (int i = 0; i < count; i++) {
			Membership m1 = a.getMembership(i);
			Membership m2 = b.getMembership(i);
			sum += m1.minus(m2).abs();
		}
		double d = (a.abs() + b.abs());
		if (d == 0)
			return 1;
		else
			return 1 - (sum / d);
	}


	public String getDescription() {
		return "M3";
	}

}
