package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzyGrayscaleImage;
import de.berlios.imilarity.image.GrayscaleImage;

public class FuzzyGrayscaleMeasure extends FastGrayscaleMeasureBase {

	private FastFuzzyMeasure measure;
	
	public FuzzyGrayscaleMeasure(FastFuzzyMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(GrayscaleImage query) {
		super.setQuery(query);
		measure.setQuery(new FuzzyGrayscaleImage(query));
	}
	
	public void setTarget(GrayscaleImage target) {
		super.setTarget(target);
		measure.setTarget(new FuzzyGrayscaleImage(target));
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
		return "Fuzzy Grayscale using " + measure.getDescription();
	}

}
