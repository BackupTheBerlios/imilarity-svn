package de.berlios.imilarity.aggregators;

public class Maximum extends AggregatorBase {

	private boolean valueAdded = false;
	private double max = 0.0; 
	
	public void addValue(double value) {
		if (!valueAdded || value > max) {
			valueAdded = true;
			max = value;
		}
	}

	public void clearValues() {
		max = 0.0;
		valueAdded = false;
	}
	
	public double getAggregatedValue() {
		return max;
	}

	
	public String getDescription() {
		return "Maximum";
	}
}
