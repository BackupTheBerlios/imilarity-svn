/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImageAdapter;
import de.berlios.imilarity.image.ScalableColorImage;
import de.berlios.imilarity.image.ScalableGrayscaleImage;

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
	
	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#similarity(image.ColorImage, image.ColorImage)
	 */
	public double similarity(ScalableColorImage ci1, ScalableColorImage ci2) {
		ScalableGrayscaleImage gi1 = new GrayscaleImageAdapter(ci1);
		ScalableGrayscaleImage gi2 = new GrayscaleImageAdapter(ci2);
		return measure.similarity(gi1, gi2);
	}

	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#getDescription()
	 */
	public String getDescription() {
		return "Grayscaled " + measure.getDescription();
	}

}
