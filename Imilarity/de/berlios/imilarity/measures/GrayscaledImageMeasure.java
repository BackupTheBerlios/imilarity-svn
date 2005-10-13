/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.GrayscaleImage;

/**
 * @author Klaas Bosteels
 */
public class GrayscaledImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public GrayscaledImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new GrayscaleImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new GrayscaleImage(image));
	}
	
	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#getSimilarity()
	 */
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#getDescription()
	 */
	public String getDescription() {
		return "Grayscaled " + measure.getDescription();
	}

}
