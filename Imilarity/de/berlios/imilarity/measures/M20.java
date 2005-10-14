/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.fuzzy.Membership;


public class M20 extends AggregatedFuzzyMeasure {

	public M20() {
		super(new ArithmeticMean());
	}
	
	public double m(Membership x, Membership y) {
		double v1 = x.abs(), v2 = y.abs();
		//System.out.println("v1 = " + v1 + " v2 = " + v2);
		if (v1 == v2) return 1;
		else if (v1 < v2) return v1 / v2;
		else return v2 / v1;
	}

	public String getDescription() {
		return "M20";
	}

}
