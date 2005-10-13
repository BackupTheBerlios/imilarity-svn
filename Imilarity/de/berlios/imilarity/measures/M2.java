/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Minimum;
import de.berlios.imilarity.fuzzy.Membership;


public class M2 extends AggregatedImageMeasure {

	public M2() {
		super(new Minimum());
	}
	
	public double m(Membership x, Membership y) {
		return 1 - x.minus(y).abs();
	}
	
	public String getDescription() {
		return "M2";
	}

}
