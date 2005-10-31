package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.HmmdImage;
import de.berlios.imilarity.image.Image;

public class HmmdImageMeasure extends ImageMeasureBase {
	
	private ImageMeasure measure;
	
	public HmmdImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new HmmdImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new HmmdImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "HMMD using " + measure.getDescription();
	}
}
