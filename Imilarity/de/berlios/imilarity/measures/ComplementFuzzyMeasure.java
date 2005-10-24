package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzySet;

public class ComplementFuzzyMeasure extends FuzzyMeasureBase {

	private FuzzyMeasure measure;
	
	public ComplementFuzzyMeasure(FuzzyMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(FuzzySet query) {
		measure.setQuery(query.complement());
		super.setQuery(query);
	}
	
	public void setTarget(FuzzySet target) {
		measure.setTarget(target.complement());
		super.setTarget(target);
	}
	
	
	public String getDescription() {
		return "Complement " + measure.getDescription();
	}

	public double getSimilarity() {
		return measure.getSimilarity();
	}
}
