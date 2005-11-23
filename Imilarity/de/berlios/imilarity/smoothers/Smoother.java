package de.berlios.imilarity.smoothers;

public interface Smoother {

	public int getRange();
	
	public int getIndex(int bin, int offset);
	
	public double getIncrement(int bin, int offset, double value);
	
}
