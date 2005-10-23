package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.Membership;


public class M3 extends FuzzyMeasureBase {
	
	public double getSimilarity() {
		double sum1 = 0.0, sum2 = 0.0;
		int count = getQuery().getElementsCount();
		for (int i = 0; i < count; i++) {
			Membership m1 = getQuery().getMembership(i);
			Membership m2 = getTarget().getMembership(i);
			sum1 += m1.minus(m2).abs();
			sum2 += m1.plus(m2).abs();
		}
		return 1 - (sum1 / sum2);
	}


	public String getDescription() {
		return "M3";
	}

}
