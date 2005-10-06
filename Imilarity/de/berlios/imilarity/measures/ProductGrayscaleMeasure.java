/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public class ProductGrayscaleMeasure extends FastGrayscaleMeasureBase {

	private FastGrayscaleMeasure measure1, measure2;
	
	public ProductGrayscaleMeasure(FastGrayscaleMeasure measure1, FastGrayscaleMeasure measure2) {
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
	
	
	public void compare(int v1, int v2) {
		measure1.compare(v1,v2);
		measure2.compare(v1,v2);
	}
	
	public double combine() {
		return measure1.combine() * measure2.combine();
	}
	
	public void reset() {
		measure1.reset();
		measure2.reset();
	}
	
	
	public String getDescription() {
		return "Product of " + measure1.getDescription() + " and " + measure2.getDescription(); 
	}

}
