package de.berlios.imilarity.smoothers;


public class NhSmoother implements Smoother {

	private int gbc, cbc, range;
	
	public NhSmoother(int gbc, int cbc, int range) {
		this.gbc = gbc;
		this.cbc = cbc;
		this.range = range;
	}
	
	public NhSmoother(int gbc, int cbc) {
		this(gbc, cbc, 4);
	}
	
	
	public int getRange() {
		return range;
	}

	public int getIndex(int bin, int offset) {
		if (bin < gbc) {
			if (bin+offset < gbc)
				return bin+offset;
			else
				return -1;
		} else {
			if (bin+offset < gbc)
				return bin+offset+cbc;
			else
				return gbc+((bin+offset-gbc)%cbc);
		}
	}

	public double getIncrement(int bin, int offset, double value) {
		return (1.0/Math.pow(2, Math.abs(offset))) * value;
		//return (1.0/Math.abs(offset))*value;
	}



}
