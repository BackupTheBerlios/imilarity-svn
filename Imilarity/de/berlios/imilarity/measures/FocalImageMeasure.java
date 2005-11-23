package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.FocalImage;
import de.berlios.imilarity.image.Image;

public class FocalImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public FocalImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new FocalImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new FocalImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Focal using " + measure.getDescription();
	}

}
