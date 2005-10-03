/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ScalableColorImage;
import de.berlios.imilarity.image.ScalableGrayscaleImage;
import de.berlios.imilarity.image.ScalableGrayscaleImageAdapter;

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
	
	public void setImage(ScalableColorImage image) {
		super.setImage(image);
		measure.setImage(new ScalableGrayscaleImageAdapter(image));
	}
	
	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#similarity(ScalableGrayscaleImage)
	 */
	public double similarity(ScalableColorImage image) {
		ScalableGrayscaleImage gi = new ScalableGrayscaleImageAdapter(image);
		return measure.similarity(gi);
	}

	/**
	 * @see de.berlios.imilarity.measures.ColorMeasure#getDescription()
	 */
	public String getDescription() {
		return "Grayscaled " + measure.getDescription();
	}

}
