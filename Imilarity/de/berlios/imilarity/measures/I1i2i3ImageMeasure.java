package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.I1i2i3Image;

public class I1i2i3ImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public I1i2i3ImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new I1i2i3Image(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new I1i2i3Image(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "I1I2I3 using " + measure.getDescription();
	}

}
