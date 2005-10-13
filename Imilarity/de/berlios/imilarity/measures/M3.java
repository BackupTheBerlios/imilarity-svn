package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.Membership;


public class M3 extends StagedFuzzyMeasureBase {

	private double sum1 = 0.0, sum2 = 0.0;
	
	public void compare(int element) {
		Membership m1 = getQuery().getMembership(element);
		Membership m2 = getTarget().getMembership(element);
		sum1 += m1.minus(m2).abs();
		sum2 += m1.plus(m2).abs();
	}

	public double combine() {
		return 1 - (sum1 / sum2);
	}

	public void reset() {
		sum1 = 0;
		sum2 = 0;
	}


	public String getDescription() {
		return "M3";
	}

}
