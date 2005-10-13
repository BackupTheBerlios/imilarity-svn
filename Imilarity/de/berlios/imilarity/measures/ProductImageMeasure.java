/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public class ProductImageMeasure extends StagedImageMeasureBase {

	private StagedImageMeasure measure1, measure2;
	
	public ProductImageMeasure(StagedImageMeasure measure1, StagedImageMeasure measure2) {
		if (measure1 == null)
			throw new NullPointerException("measure1 == null");
		this.measure1 = measure1;
		if (measure2 == null)
			throw new NullPointerException("measure2 == null");
		this.measure2 = measure2;
	}
	
	public void setQuery(GrayscaleImage image) {
		super.setQuery(image);
		measure1.setQuery(image);
		measure2.setQuery(image);
	}
	
	public void setTarget(GrayscaleImage image) {
		super.setTarget(image);
		measure1.setTarget(image);
		measure2.setTarget(image);
	}
	
	
	public void compare(int pixelNr) {
		measure1.compare(pixelNr);
		measure2.compare(pixelNr);
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
