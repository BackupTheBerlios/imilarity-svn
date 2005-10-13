/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.fuzzy.Membership;


public class M20 extends AggregatedImageMeasure {

	public M20() {
		super(new ArithmeticMean());
	}
	
	public double m(Membership x, Membership y) {
		if (x.equals(y)) return 1;
		else if (x.abs() < y.abs()) return x.abs() / y.abs();
		else return y.abs() / x.abs();
	}

	public String getDescription() {
		return "M20";
	}

}
