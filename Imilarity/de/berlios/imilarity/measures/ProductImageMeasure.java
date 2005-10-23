/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;

public class ProductImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure1, measure2;
	
	public ProductImageMeasure(ImageMeasure measure1, ImageMeasure measure2) {
		if (measure1 == null)
			throw new NullPointerException("measure1 == null");
		this.measure1 = measure1;
		if (measure2 == null)
			throw new NullPointerException("measure2 == null");
		this.measure2 = measure2;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure1.setQuery(image);
		measure2.setQuery(image);
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure1.setTarget(image);
		measure2.setTarget(image);
	}
	
	public double getSimilarity() {
		double sim1 = measure1.getSimilarity();
		double sim2 = measure2.getSimilarity();
		return sim1 * sim2;
	}

	public String getDescription() {
		return "Product of " + measure1.getDescription() + " and " + measure2.getDescription(); 
	}
}
