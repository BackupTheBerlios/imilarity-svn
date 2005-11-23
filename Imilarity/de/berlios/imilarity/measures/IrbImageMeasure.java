package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.IrbImage;

public class IrbImageMeasure extends ImageMeasureBase {
private ImageMeasure measure;
	
	public IrbImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new IrbImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new IrbImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Irb using " + measure.getDescription();
	}

}
