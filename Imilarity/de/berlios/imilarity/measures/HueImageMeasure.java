package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.HueImage;

public class HueImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public HueImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new HueImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new HueImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Hue using " + measure.getDescription();
	}

}
