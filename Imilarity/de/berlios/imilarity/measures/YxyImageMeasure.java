package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.YxyImage;

public class YxyImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public YxyImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new YxyImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new YxyImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Yxy using " + measure.getDescription();
	}

}
