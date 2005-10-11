/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public abstract class AvarageGrayscaleMeasure extends FastGrayscaleMeasureBase {

	private double sum = 0.0;
	
	public abstract double m(double x, double y);
	
	
	public void compare(int pixelNr) {
		double nv1 = getQuery().getGrayscaleValue(pixelNr) * 1.0 / 255;
		double nv2 = getTarget().getGrayscaleValue(pixelNr) * 1.0 / 255;
		sum += m(nv1,nv2);
	}
	
	public double combine() {
		GrayscaleImage orig = getQuery();
		int pc = orig.getWidth() * orig.getHeight();
		return sum / pc;
	}

	public void reset() {
		sum = 0.0;
	}
}
