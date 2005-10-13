package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.Membership;

public class PM1a extends M1a {

	private int counter = 1, divisor = 1;
	
	public void setQuery(FuzzySet query) {
		super.setQuery(query);
		int elementsCount = query.getElementsCount();
		divisor = elementsCount * (elementsCount+1) / 2;
	}
	
	public double m(Membership x, Membership y) {
		return 1 - ((counter++ * 1.0 / divisor) * x.minus(y).abs());
	}
	
	public void reset() {
		super.reset();
		counter = 0;
		int elementsCount = getQuery().getElementsCount() + 1;
		divisor = elementsCount * (elementsCount+1) / 2;
	}
	
	public String getDescription() {
		return "PM1a";
	}
}
