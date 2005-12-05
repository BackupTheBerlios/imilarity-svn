package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.SctImage;
import de.berlios.imilarity.image.Image;

public class SctImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	private int grayscaleBinsCount;
	private int colorBinsCount;
	
	public SctImageMeasure(ImageMeasure measure, int gbc, int cbc) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		this.grayscaleBinsCount = gbc;
		this.colorBinsCount = cbc;
	}
	
	public SctImageMeasure(ImageMeasure measure) {
		this(measure, 16, 240);
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new SctImage(image, grayscaleBinsCount, colorBinsCount));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new SctImage(image, grayscaleBinsCount, colorBinsCount));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "NH ("+grayscaleBinsCount+","+colorBinsCount+") using " + measure.getDescription();
	}

}
