/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.Arrays;

import de.berlios.imilarity.image.GrayscaleImage;

public class OH3 extends GrayscaleMeasureBase {

	private int[] origHistogram;
	
	public void setImage(GrayscaleImage image) {
		super.setImage(image);
		origHistogram = image.getHistogram();
		Arrays.sort(origHistogram);
	}
	
	public double similarity(GrayscaleImage image) {
		int[] histogram = image.getHistogram();
		Arrays.sort(histogram);
		double sum1 = 0.0, sum2 = 0.0;
		for (int i = 0; i < histogram.length; i++) {
			double v1 = origHistogram[i] * 1.0 / 255;
			double v2 = histogram[i] * 1.0 / 255;
			sum1 += Math.abs(v1 - v2);
			sum2 += v1 + v2;
		}
		return 1 - (sum1 / sum2);
	}

	public String getDescription() {
		return "OH3";
	}

}
