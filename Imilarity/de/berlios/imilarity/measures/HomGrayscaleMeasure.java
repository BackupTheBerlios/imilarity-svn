/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public class HomGrayscaleMeasure extends GrayscaleMeasureBase {

	private GrayscaleMeasure measure;
	
	public HomGrayscaleMeasure(GrayscaleMeasure measure) {
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
	
	public double similarity(GrayscaleImage image) {
		// TODO: dit efficienter doen 
		// (bvb. door interface van GrayscaleMeasure uit te breiden...)
		GrayscaleImage orig = getImage();
		if (image == null || !sameResolution(orig,image))
			return 0.0;
		int pc = orig.getWidth() * orig.getHeight();
		double max1 = 0.0, max2 = 0.0, min1 = 1.0, min2 = 1.0;
		for (int i = 0; i < pc; i++) {
			double v1 = orig.getGrayscaleValue(i);
			double v2 = image.getGrayscaleValue(i);
			if (v1 > max1) max1 = v1;
			if (v1 < min1) min1 = v1;
			if (v2 > max2) max2 = v2;
			if (v2 < min2) min2 = v2;
		}
		double hom1 = s(max1,min1);
		double hom2 = s(max2,min2);
		return s(hom1, hom2) * measure.similarity(image);
	}
	
	public String getDescription() {
		return "Hom " + measure.getDescription();
	}
}
