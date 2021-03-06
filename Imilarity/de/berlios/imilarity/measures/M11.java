package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M11 extends M5 {

	private FuzzySet a, b;
	
	public void setQuery(FuzzySet set) {
		a = set;
		setInternalQueryAndTarget();
	}

	public FuzzySet getQuery() {
		return a;
	}
	
	public void setTarget(FuzzySet set) {
		b = set;
		setInternalQueryAndTarget();
	}
	
	public FuzzySet getTarget() {
		return b;
	}
	
	private void setInternalQueryAndTarget() {
		if (a != null && b != null) {
			super.setQuery(a.minus(b));
			super.setTarget(b.minus(a));
		}
	}

	public String getDescription() {
		return "M11";
	}

}
