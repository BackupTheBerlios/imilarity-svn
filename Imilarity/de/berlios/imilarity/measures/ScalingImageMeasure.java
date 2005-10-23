/*
 * Created on 23-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;


/**
 * @author Klaas Bosteels
 */
public class ScalingImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	
	public ScalingImageMeasure(ImageMeasure measure) {
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
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
