package de.berlios.imilarity.aggregators;

public class Maximum extends AggregatorBase {

	private Double max = null; 
	
	public void addValue(double value) {
		if (max == null || value > max.doubleValue())
			max = new Double(value);
	}

	public void clearValues() {
		max = null;
	}
	
	public double getAggregatedValue() {
		return max.doubleValue();
	}

	
	public String getDescription() {
		return "Maximum";
	}
}
