/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.ArithmeticMean;


public class M1a extends AggregatedGrayscaleMeasure {

	public M1a() {
		super(new ArithmeticMean());
	}
	
	public double m(double x, double y) {
		return 1 - Math.abs(x - y);
	}

	public String getDescription() {
		return "M1a";
	}

}
