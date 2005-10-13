/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.aggregators;


/**
 * @author Klaas Bosteels
 */
public abstract class AggregatorBase implements Aggregator {
	
	public String toString() {
		return getDescription();
	}

}
