/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Aggregator;

public abstract class AggregatedGrayscaleMeasure extends FastFuzzyMeasureBase {

	private Aggregator aggregator;
	
	public AggregatedGrayscaleMeasure(Aggregator aggregator) {
		if (aggregator == null)
			throw new NullPointerException("aggregator = null");
		this.aggregator = aggregator;
	}
	
	public abstract double m(double x, double y);
	
	
	public void compare(int pixelNr) {
		double v1 = getQuery().getMembership(pixelNr);
		double v2 = getTarget().getMembership(pixelNr);
		aggregator.addValue(m(v1,v2));
	}
	
	public double combine() {
		return aggregator.getAggregatedValue();
	}

	public void reset() {
		aggregator.clearValues();
	}
}
