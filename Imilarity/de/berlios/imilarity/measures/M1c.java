package de.berlios.imilarity.measures;

public class M1c extends FuzzyMeasureBase {

	public double getSimilarity() {
		int count = getQuery().getElementsCount();
		double sum = 0.0;
		for (int i = 0; i < count; i++)
			sum += Math.pow(getQuery().getMembership(i).minus(getTarget().getMembership(i)).abs(),
					1.0/4.0);
		return 1 - (sum / count);
	}

	public String getDescription() {
		return "M1c";
	}

}
