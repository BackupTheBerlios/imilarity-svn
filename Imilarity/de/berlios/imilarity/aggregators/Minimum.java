package de.berlios.imilarity.aggregators;

public class Minimum extends AggregatorBase {

	private boolean valueAdded = false;
	private double min = 0.0; 
	
	public void addValue(double value) {
		if (!valueAdded || value < min) {
			valueAdded = true;
			min = value;
		}
	}

	public void clearValues() {
		min = 0.0;
		valueAdded = false;
	}
	
	public double getAggregatedValue() {
		return min;
	}

	
	public String getDescription() {
		return "Minimum";
	}
}
