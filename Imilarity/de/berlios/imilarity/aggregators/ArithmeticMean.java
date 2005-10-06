/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.aggregators;

/**
 * @author Klaas Bosteels
 */
public class ArithmeticMean extends AggregatorBase {

	/**
	 * @see de.berlios.imilarity.aggregators.Aggregator#aggregatedValue(int[])
	 */
	public int aggregatedValue(int[] values) {
		int sum = 0;
		for (int i = 0; i < values.length; i++)
			sum += values[i];
		return (int) Math.round(sum * 1.0 / values.length);
	}

	/**
	 * @see de.berlios.imilarity.aggregators.Aggregator#getDescription()
	 */
	public String getDescription() {
		return "Arithmetic Mean";
	}

}
