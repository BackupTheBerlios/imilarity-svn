package de.berlios.imilarity.measures;

public class GTI extends StagedFuzzyMeasureBase {

	private double a, b, sum1 = 0, sum2 = 0;
	
	public GTI(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public void compare(int element) {
		double v1 = getQuery().getMembership(element);
		double v2 = getTarget().getMembership(element);
		sum1 += Math.min(v1,v2);
		sum2 += Math.min(v1,v2) + a * Math.min(v1,1-v2) + b * Math.min(1-v1,v2);
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
