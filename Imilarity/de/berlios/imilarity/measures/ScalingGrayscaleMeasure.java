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
	
	public void setQuery(GrayscaleImage image) {
		super.setQuery(image);
		measure.setQuery(image);
	}
	
	public void setTarget(GrayscaleImage image) {
		GrayscaleImage query = getQuery();
		image = image.getScaledInstance(query.getWidth(),query.getHeight());
		super.setTarget(image);
		measure.setTarget(image);
	}
	
	
	public void compare(int pixelNr) {
		measure.compare(pixelNr);
	}
	
	public double combine() {
		return measure.combine();
	}
	
	public void reset() {
		measure.reset();
	}
	
	public double getSimilarity() {
		GrayscaleImage query = getQuery();
		GrayscaleImage target = getTarget();
		if (query == null || target == null)
			return 0.0;
		return measure.getSimilarity();
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
