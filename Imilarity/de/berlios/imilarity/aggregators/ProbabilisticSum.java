package de.berlios.imilarity.aggregators;

public class ProbabilisticSum extends AggregatorBase {
	
	private boolean valueAdded = false;
	private double sum = 0.0;
	
	public void addValue(double value) {
		if (!valueAdded) {
			valueAdded = true;
			sum = value;
		}
		else
			sum += value - (value*sum);
	}

	public void clearValues() {
		sum = 0.0;
		valueAdded = false;
	}

	public double getAggregatedValue() {
		return sum;
	}

	public String getDescription() {
		return "Probabilistic Sum";
	}

}
