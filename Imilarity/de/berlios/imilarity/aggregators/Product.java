package de.berlios.imilarity.aggregators;

public class Product extends AggregatorBase {

	private boolean valueAdded = false;
	private double product = 0.0;
	
	public void addValue(double value) {
		if (!valueAdded) {
			valueAdded = true;
			product = value;
		}
		else
			product *= value;
	}

	public void clearValues() {
		product = 0.0;
		valueAdded = false;
	}

	public double getAggregatedValue() {
		return product;
	}

	public String getDescription() {
		return "Product";
	}

}
