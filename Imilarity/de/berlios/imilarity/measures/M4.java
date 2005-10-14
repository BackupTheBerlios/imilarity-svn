/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.fuzzy.Membership;


public class M4 extends AggregatedFuzzyMeasure {

	public M4() {
		super(new ArithmeticMean());
	}
	
	public double m(Membership mx, Membership my) {
		double x = mx.abs();
		double y = my.abs();
		return 1 - 
			(((x-y)*Math.log((1+x)/(1+y))+(y-x)*Math.log((2-x)/(2-y))) / (2*Math.log(2)));
	}

	public String getDescription() {
		return "M4";
	}

}
