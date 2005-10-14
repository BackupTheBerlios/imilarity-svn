/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.fuzzy.Membership;


public class M1a extends AggregatedFuzzyMeasure {

	public M1a() {
		super(new ArithmeticMean());
	}
	
	public double m(Membership x, Membership y) {
		return 1 - x.minus(y).abs();
	}

	public String getDescription() {
		return "M1a";
	}
}
