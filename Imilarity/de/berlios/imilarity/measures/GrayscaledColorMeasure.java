/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ColorImage;
import de.berlios.imilarity.image.GrayscaleImageAdapter;

/**
 * @author Klaas Bosteels
 */
public class GrayscaledColorMeasure extends ColorMeasureBase {

	private GrayscaleMeasure measure;
	
	public GrayscaledColorMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(ColorImage image) {
		super.setQuery(image);
		measure.setQuery(new GrayscaleImageAdapter(image));
	}
	
	public void setTarget(ColorImage image) {
		super.setTarget(image);
		measure.setTarget(new GrayscaleImageAdapter(image));
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
