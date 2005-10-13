/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.ArithmeticMean;


public class M20 extends AggregatedGrayscaleMeasure {

	public M20() {
		super(new ArithmeticMean());
	}
	
	public double m(double x, double y) {
		if (x == y) return 1;
		else if (x < y) return x / y;
		else return y / x;
	}

	public String getDescription() {
		return "M20";
	}

}
