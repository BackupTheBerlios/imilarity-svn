package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzyHistogram;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.LabImage;
import de.berlios.imilarity.image.quantizers.Quantizer;
import de.berlios.imilarity.image.quantizers.UniformQuantizer;

public class FuzzyHistogramImageMeasure extends ImageMeasureBase {

	private FuzzyMeasure measure;
	private Quantizer quantizer;
	
	public FuzzyHistogramImageMeasure(FuzzyMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		this.quantizer = new UniformQuantizer(new int[] {10,10,10}); // reduceren tot 1000 kleuren
	}
	
	public void setQuery(Image query) {
		super.setQuery(query);
		measure.setQuery(new FuzzyHistogram(new LabImage(query), quantizer));
	}
	
	public void setTarget(Image target) {
		super.setTarget(target);
		measure.setTarget(new FuzzyHistogram(new LabImage(target), quantizer));
	}

	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Fuzzy Histogam using " + measure.getDescription() + " and " 
			+ quantizer.getDescription() + " quantizer";
	}

}
