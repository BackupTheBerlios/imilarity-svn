package de.berlios.imilarity.measures;


public class M3 extends FastFuzzyMeasureBase {

	private double sum1 = 0.0, sum2 = 0.0;
	
	public void compare(int element) {
		double v1 = getQuery().getMembership(element);
		double v2 = getTarget().getMembership(element);
		sum1 += Math.abs(v1 - v2);
		sum2 += v1 + v2;
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
