package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.fuzzy.Membership;

public class M1d extends SimplifiedFuzzyMeasure {

	private static final double EXPON = 1.0/4.0;
	
	public M1d() {
		super(new ArithmeticMean());
	}
	
	public double m(Membership x, Membership y) {
		return 1 - Math.pow(x.minus(y).abs(), EXPON);
	}

	public String getDescription() {
		return "M1d";
	}

}
