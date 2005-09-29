/*
 * Created on 29-sep-2005
 */
package aggregators;

/**
 * @author Klaas Bosteels
 */
public abstract class AggregatorBase implements Aggregator {

	public String toString() {
		return getDescription();
	}

}
