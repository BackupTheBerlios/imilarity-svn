/*
 * Created on 23-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class ScalingGrayscaleMeasure extends GrayscaleMeasureBase {

	private GrayscaleMeasure measure;
	
	private static final int WIDTH = 100, HEIGHT = 100;
	
	public ScalingGrayscaleMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public double similarity(ScalableGrayscaleImage gi1, ScalableGrayscaleImage gi2) {
		ScalableGrayscaleImage sGi1 = gi1.getScaledInstance(WIDTH,HEIGHT);
		ScalableGrayscaleImage sGi2 = gi2.getScaledInstance(WIDTH,HEIGHT);
		return measure.similarity(sGi1,sGi2);
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
