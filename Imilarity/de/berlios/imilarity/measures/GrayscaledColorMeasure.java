/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ColorImage;
import de.berlios.imilarity.image.GrayscaleImage;
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
	
	public void setImage(ColorImage image) {
		super.setImage(image);
		measure.setImage(new GrayscaleImageAdapter(image));
	}
	
	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#similarity(GrayscaleImage)
	 */
	public double similarity(ColorImage image) {
		GrayscaleImage gi = new GrayscaleImageAdapter(image);
		return measure.similarity(gi);
	}

	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#getDescription()
	 */
	public String getDescription() {
		return "Grayscaled " + measure.getDescription();
	}

}
