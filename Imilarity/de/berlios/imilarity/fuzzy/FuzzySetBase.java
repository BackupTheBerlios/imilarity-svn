package de.berlios.imilarity.fuzzy;

public abstract class FuzzySetBase implements FuzzySet {

	public double abs() {
		if (getElementsCount() == 0)
			return 0.0;
		else {
			double sum = 0.0;
			for (int i = 0; i < getElementsCount(); i++) {
				double part = 0.0;
				double[] components = getMembership(i).getComponents();
				for (int j = 0; j < components.length; j++)
					part += components[j]*components[j];
				sum += Math.sqrt(part);
			}
			return sum / Math.sqrt(getMembership(0).getComponents().length);
		}
	}
}
