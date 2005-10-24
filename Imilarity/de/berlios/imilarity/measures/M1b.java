package de.berlios.imilarity.measures;

public class M1b extends FuzzyMeasureBase {

	public double getSimilarity() {
		int count = getQuery().getElementsCount();
		double sum = 0.0;
		for (int i = 0; i < count; i++)
			sum += Math.sqrt(getQuery().getMembership(i).minus(getTarget().getMembership(i)).abs());
		return 1 - (sum / count);
	}

	public String getDescription() {
		return "M1b";
	}

}
