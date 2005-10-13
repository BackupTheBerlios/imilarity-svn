/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;

public class HomImageMeasure extends GrayscaleStagedImageMeasureBase {

	private StagedImageMeasure measure;
	private double max1 = 0.0, max2 = 0.0, min1 = 1.0, min2 = 1.0;
	
	public HomImageMeasure(StagedImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(image);
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(image);
	}

	
	private static double s(double x, double y) {
		return Math.min(1, Math.max(0, 6/5 - 2 * Math.abs(x - y)));
	}
	
	public void compare(int pixelNr) {
		double nv1 = measure.getQuery().getColor(pixelNr).getComponents()[0];
		double nv2 = measure.getTarget().getColor(pixelNr).getComponents()[0];
		if (nv1 > max1) max1 = nv1;
		if (nv1 < min1) min1 = nv1;
		if (nv2 > max2) max2 = nv2;
		if (nv2 < min2) min2 = nv2;
		measure.compare(pixelNr);
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
