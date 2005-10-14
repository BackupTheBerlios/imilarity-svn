/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.fuzzy.Membership;


public abstract class AggregatedFuzzyMeasure extends StagedFuzzyMeasureBase {

	private Aggregator aggregator;
	
	public AggregatedFuzzyMeasure(Aggregator aggregator) {
		if (aggregator == null)
			throw new NullPointerException("aggregator = null");
		this.aggregator = aggregator;
	}
	
	public abstract double m(Membership x, Membership y);
	
	public void compare(int element) {
		Membership m1 = getQuery().getMembership(element);
		Membership m2 = getTarget().getMembership(element);
		aggregator.addValue(m(m1,m2));
	}
	
	public double combine() {
		return aggregator.getAggregatedValue();
	}

	public void reset() {
		aggregator.clearValues();
	}
}
