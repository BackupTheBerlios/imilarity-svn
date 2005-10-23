package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.Membership;

public class PM1a extends M1a {

	private int counter, divisor;
	
	public double m(Membership x, Membership y) {
		return 1 - ((counter++ * 1.0 / divisor) * x.minus(y).abs());
	}
	
	public double getSimilarity() {
		int elementsCount = getQuery().getElementsCount();
		counter = 1;
		divisor = elementsCount * (elementsCount+1) / 2;
		return super.getSimilarity();
	}
	
	public String getDescription() {
		return "PM1a";
	}
}
