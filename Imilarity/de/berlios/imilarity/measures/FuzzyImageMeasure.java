package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzyImage;
import de.berlios.imilarity.image.Image;

public class FuzzyImageMeasure extends ImageMeasureBase {

	private FuzzyMeasure measure;
	
	public FuzzyImageMeasure(FuzzyMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image query) {
		super.setQuery(query);
		measure.setQuery(new FuzzyImage(query));
	}
	
	public void setTarget(Image target) {
		if (!sameResolution(getQuery(), target))
			throw new IllegalArgumentException("query and target must have same resolution");
		super.setTarget(target);
		measure.setTarget(new FuzzyImage(target));
	}

	
	public double getSimilarity() {
		return measure.getSimilarity();
	}
	
	
	public String getDescription() {
		return "Fuzzy using " + measure.getDescription();
	}

}
