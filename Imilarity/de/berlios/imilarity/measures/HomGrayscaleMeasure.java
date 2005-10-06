/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public class HomGrayscaleMeasure extends FastGrayscaleMeasureBase {

	private FastGrayscaleMeasure measure;
	private double max1 = 0.0, max2 = 0.0, min1 = 1.0, min2 = 1.0;
	
	public HomGrayscaleMeasure(FastGrayscaleMeasure measure) {
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
	
	private static double s(double x, double y) {
		return Math.min(1, Math.max(0, 6/5 - 2 * Math.abs(x - y)));
	}
	
	public void compare(int v1, int v2) {
		double nv1 = v1 * 1.0 / 255;
		double nv2 = v2 * 1.0 / 255;
		if (nv1 > max1) max1 = nv1;
		if (nv1 < min1) min1 = nv1;
		if (nv2 > max2) max2 = nv2;
		if (nv2 < min2) min2 = nv2;
		measure.compare(v1, v2);
	}
	
	public double combine() {
		double hom1 = s(max1,min1);
		double hom2 = s(max2,min2);
		return s(hom1, hom2) * measure.combine();	
	}
	
	public void reset() {
		max1 = 0.0;
		max2 = 0.0;
		min1 = 1.0;
		min2 = 1.0;
		measure.reset();
	}
	
	
	public String getDescription() {
		return "Hom " + measure.getDescription();
	}
}
