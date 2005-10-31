package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.XyzImage;

public class XyzImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public XyzImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new XyzImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new XyzImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "XYZ using " + measure.getDescription();
	}

}
