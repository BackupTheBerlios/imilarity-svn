/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.aggregators;


/**
 * @author Klaas Bosteels
 */
public class ArithmeticMean extends AggregatorBase {

	private double sum = 0;
	private int count = 0;
	
	public void addValue(double value) {
		sum += value;
		count++;
	}

	public void clearValues() {
		sum = 0;
		count = 0;
	}
	
	/**
	 * @see de.berlios.imilarity.aggregators.Aggregator#getAggregatedValue()
	 */
	public double getAggregatedValue() {
		return sum / count;
	}

	
	/**
	 * @see de.berlios.imilarity.aggregators.Aggregator#getDescription()
	 */
	public String getDescription() {
		return "Arithmetic Mean";
	}



}
