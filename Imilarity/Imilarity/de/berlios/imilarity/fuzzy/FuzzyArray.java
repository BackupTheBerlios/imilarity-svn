package de.berlios.imilarity.fuzzy;

public class FuzzyArray implements FuzzySet {

	private double[] memberships;
	
	public FuzzyArray(double[] memberships) {
		if (memberships == null)
			throw new NullPointerException("memberships == null");
		this.memberships = memberships;
	}
	
	public int getElementsCount() {
		return memberships.length;
	}

	public double getMembership(int element) {
		return memberships[element];
	}

}
