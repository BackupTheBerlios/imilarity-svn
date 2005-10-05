/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public class ProductGrayscaleMeasure extends GrayscaleMeasureBase {

	private GrayscaleMeasure measure1, measure2;
	
	public ProductGrayscaleMeasure(GrayscaleMeasure measure1, GrayscaleMeasure measure2) {
		if (measure1 == null)
			throw new NullPointerException("measure1 == null");
		this.measure1 = measure1;
		if (measure2 == null)
			throw new NullPointerException("measure2 == null");
		this.measure2 = measure2;
	}
	
	public void setImage(GrayscaleImage image) {
		measure1.setImage(image);
		measure2.setImage(image);
	}
	
	public GrayscaleImage getImage() {
		return measure1.getImage();
	}
	
	public double similarity(GrayscaleImage image) {
		return measure1.similarity(image) * measure2.similarity(image);
	}
	
	public String getDescription() {
		return "Product of " + measure1.getDescription() + " and " + measure2.getDescription(); 
	}

}
