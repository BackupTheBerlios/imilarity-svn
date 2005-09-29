/*
 * Created on 23-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;
import de.berlios.imilarity.image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class ScalingGrayscaleMeasure extends GrayscaleMeasureBase {

	private GrayscaleMeasure measure;
	
	public ScalingGrayscaleMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setImage(GrayscaleImage image) {
		measure.setImage(image);
	}
	
	public GrayscaleImage getImage() {
		return measure.getImage();
	}
	
	public double similarity(ScalableGrayscaleImage image) {
		GrayscaleImage orig = getImage();
		ScalableGrayscaleImage si = image.getScaledInstance(orig.getWidth(),orig.getHeight());
		return measure.similarity(si);
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
