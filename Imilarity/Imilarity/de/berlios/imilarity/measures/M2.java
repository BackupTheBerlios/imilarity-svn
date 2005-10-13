/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Minimum;


public class M2 extends AggregatedGrayscaleMeasure {

	public M2() {
		super(new Minimum());
	}
	
	public double m(double x, double y) {
		return 1 - Math.abs(x - y);
	}
	
	public String getDescription() {
		return "M2";
	}

}
