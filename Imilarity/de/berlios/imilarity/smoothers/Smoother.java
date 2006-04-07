package de.berlios.imilarity.smoothers;

import de.berlios.imilarity.util.Describable;

public interface Smoother extends Describable {

	public int getRange();
	
	public int getIndex(int bin, int offset);
	
	public double getIncrement(int bin, int offset, double value);

}
