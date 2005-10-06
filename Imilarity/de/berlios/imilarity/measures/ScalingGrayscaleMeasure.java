/*
 * Created on 23-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class ScalingGrayscaleMeasure extends FastGrayscaleMeasureBase {

	private FastGrayscaleMeasure measure;
	
	public ScalingGrayscaleMeasure(FastGrayscaleMeasure measure) {
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
	
	public void compare(int v1, int v2) {
		measure.compare(v1,v2);
	}
	
	public double combine() {
		return measure.combine();
	}
	
	public void reset() {
		measure.reset();
	}
	
	public double similarity(GrayscaleImage image) {
		GrayscaleImage orig = getImage();
		if (orig == null || image == null)
			return 0.0;
		GrayscaleImage si = image.getScaledInstance(orig.getWidth(),orig.getHeight());
		return measure.similarity(si);
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
