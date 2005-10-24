/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.fuzzy.Membership;


public abstract class SimplifiedFuzzyMeasure extends FuzzyMeasureBase {

	private Aggregator aggregator;
	
	public SimplifiedFuzzyMeasure(Aggregator aggregator) {
		if (aggregator == null)
			throw new NullPointerException("aggregator = null");
		this.aggregator = aggregator;
	}
	
	public abstract double m(Membership x, Membership y);
	
	public double getSimilarity() {
		aggregator.clearValues();
		int count = getQuery().getElementsCount();
		for (int i = 0; i < count; i++) {
			Membership m1 = getQuery().getMembership(i);
			Membership m2 = getTarget().getMembership(i);
			aggregator.addValue(m(m1,m2));
		}
		return aggregator.getAggregatedValue();
	}
}
