package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.HsvImage;
import de.berlios.imilarity.image.Image;

public class HsvImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public HsvImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new HsvImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new HsvImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "HSV using " + measure.getDescription();
	}

}
