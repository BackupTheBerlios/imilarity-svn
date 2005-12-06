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
	
	public FuzzySet intersection(FuzzySet set) {
		int count = getElementsCount();
		if (set.getElementsCount() != count)
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet();
		for (int i = 0; i < count; i++) {
			Membership m = getMembership(i);
			result.addMembership(new SimpleMembership(m.and(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		int count = getElementsCount();
		if (set.getElementsCount() != count)
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet();
		for (int i = 0; i < count; i++) {
			Membership m = getMembership(i);
			result.addMembership(new SimpleMembership(m.or(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet complement() {
		int count = getElementsCount();
		ArrayFuzzySet result = new ArrayFuzzySet();
		for (int i = 0; i < count; i++) {
			Membership m = getMembership(i);
			result.addMembership(new SimpleMembership(m.complement().getComponents()));
		}
		return result;
	}
}
