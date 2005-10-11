/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.aggregators;

/**
 * @author Klaas Bosteels
 */
public interface Aggregator {

	public void addValue(double value);
	public void clearValues();
	
	public double getAggregatedValue();
	
	public String getDescription();
	
}
