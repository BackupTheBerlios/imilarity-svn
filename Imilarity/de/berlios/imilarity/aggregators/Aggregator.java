/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.aggregators;

/**
 * @author Klaas Bosteels
 */
public interface Aggregator {

	public int aggregatedValue(int[] values);
	
	public String getDescription();
	
}
