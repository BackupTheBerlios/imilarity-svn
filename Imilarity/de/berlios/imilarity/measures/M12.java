package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class M12 extends M7 {
	
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
			super.setQuery(b.union(a.complement()));
			super.setTarget(a.union(b.complement()));
		}
	}

	public String getDescription() {
		return "M12";
	}
}
