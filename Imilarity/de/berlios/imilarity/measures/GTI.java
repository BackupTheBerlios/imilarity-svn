package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.Membership;

public class GTI extends StagedFuzzyMeasureBase {

	private double a, b, sum1 = 0, sum2 = 0;
	
	public GTI(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public void compare(int element) {
		Membership m1 = getQuery().getMembership(element);
		Membership m2 = getTarget().getMembership(element);
		sum1 += m1.or(m2).abs();
		sum2 += m1.or(m2).abs() + a * Math.min(m1.abs(), 1 - m2.abs()) 
			+ b * Math.min(1 - m1.abs(), m2.abs());
	}

	public double combine() {
		return sum1 / sum2;
	}

	public void reset() {
		sum1 = 0;
		sum2 = 0;
	}

	public String getDescription() {
		return "GTI with a = " + a + " and b = " + b;
	}

}
