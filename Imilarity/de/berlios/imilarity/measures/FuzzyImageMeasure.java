package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzyImage;
import de.berlios.imilarity.image.Image;

public class FuzzyImageMeasure extends StagedImageMeasureBase {

	private StagedFuzzyMeasure measure;
	
	public FuzzyImageMeasure(StagedFuzzyMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image query) {
		super.setQuery(query);
		measure.setQuery(new FuzzyImage(query));
	}
	
	public void setTarget(Image target) {
		super.setTarget(target);
		measure.setTarget(new FuzzyImage(target));
	}
	 
	public void compare(int pixelNr) {
		measure.compare(pixelNr);
	}

	public double combine() {
		return measure.combine();
	}

	public void reset() {
		measure.reset();
	}

	public String getDescription() {
		return "Fuzzy using " + measure.getDescription();
	}

}
