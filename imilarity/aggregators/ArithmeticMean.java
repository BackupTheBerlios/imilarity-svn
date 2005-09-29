/*
 * Created on 29-sep-2005
 */
package aggregators;

/**
 * @author Klaas Bosteels
 */
public class ArithmeticMean extends AggregatorBase {

	/**
	 * @see aggregators.Aggregator#aggregatedValue(int[])
	 */
	public int aggregatedValue(int[] values) {
		int sum = 0;
		for (int i = 0; i < values.length; i++)
			sum += values[i];
		return (int) Math.round(sum * 1.0 / values.length);
	}

	/**
	 * @see aggregators.Aggregator#getDescription()
	 */
	public String getDescription() {
		return "Arithmetic Mean";
	}

}
