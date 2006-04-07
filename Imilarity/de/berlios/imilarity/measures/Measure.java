package de.berlios.imilarity.measures;

import de.berlios.imilarity.util.Describable;

public interface Measure extends Describable {
	
	/**
	 * @return een getal in het eenheidsinterval [0,1]
	 */
	public double getSimilarity();

}
