/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;


public abstract class MinimumGrayscaleMeasure extends FastGrayscaleMeasureBase {

	private double min = 1.0;
	
	public abstract double m(double x, double y);
	
	public void compare(int v1, int v2) {
		double nv1 = v1 * 1.0 / 255;
		double nv2 = v2 * 1.0 / 255;
		double v = m(nv1,nv2);
		if (v < min)
			min = v;
	}
	
	public double combine() {
		return min;
	}

	public void reset() {
		min = 1.0;
	}

}
