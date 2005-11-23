package de.berlios.imilarity.smoothers;


public class DefaultSmoother implements Smoother { 
	
	public int getRange() {
		return 0;
	}

	public int getIndex(int bin, int offset) {
		return bin;
	}

	public double getIncrement(int bin, int offset, double value) {
		return value;
	}

}
