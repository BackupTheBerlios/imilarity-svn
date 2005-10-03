/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public abstract class MinimumGrayscaleMeasure extends GrayscaleMeasureBase {

	public abstract double m(double x, double y);
	
	public double similarity(GrayscaleImage image) {
		GrayscaleImage orig = getImage();
		if (image == null || !sameResolution(orig,image))
			return 0.0;
		int pc = orig.getWidth() * orig.getHeight();
		double min = 1.0;
		for (int i = 0; i < pc; i++) {
			double v1 = orig.getGrayscaleValue(i) * 1.0 / 255;
			double v2 = image.getGrayscaleValue(i) * 1.0 / 255;
			double v = m(v1,v2);
			if (v < min)
				min = v;
		}
		return min;
	}

}
