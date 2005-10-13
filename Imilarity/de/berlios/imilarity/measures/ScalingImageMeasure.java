/*
 * Created on 23-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;


/**
 * @author Klaas Bosteels
 */
public class ScalingImageMeasure extends StagedImageMeasureBase {

	private StagedImageMeasure measure;
	
	public ScalingImageMeasure(StagedImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(image);
	}
	
	public void setTarget(Image image) {
		Image query = getQuery();
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
		Image query = getQuery();
		Image target = getTarget();
		if (query == null || target == null)
			return 0.0;
		return measure.getSimilarity();
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
