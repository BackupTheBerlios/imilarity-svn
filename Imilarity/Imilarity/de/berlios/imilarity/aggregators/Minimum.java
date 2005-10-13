package de.berlios.imilarity.aggregators;

public class Minimum extends AggregatorBase {

	private Double min = null; 
	
	public void addValue(double value) {
		if (min == null || value < min.doubleValue())
			min = new Double(value);
	}

	public void clearValues() {
		min = null;
	}
	
	public double getAggregatedValue() {
		return min.doubleValue();
	}

	
	public String getDescription() {
		return "Minimum";
	}
}
