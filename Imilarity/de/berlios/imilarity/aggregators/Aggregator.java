/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.aggregators;

import de.berlios.imilarity.util.Describable;

/**
 * @author Klaas Bosteels
 */
public interface Aggregator extends Describable {

	public void addValue(double value);
	public void clearValues();
	
	public double getAggregatedValue();

}
