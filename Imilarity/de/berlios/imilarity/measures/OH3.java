/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.Arrays;


public class OH3 extends FastGrayscaleMeasureBase {

	private int[] origHistogram, histogram;
	
	public OH3() {
		origHistogram = new int[256];
		histogram = new int[256];
		for (int i = 0; i < 256; i++) {
			origHistogram[i] = 0;
			histogram[i] = 0;
		}
	}
	
	
	public void compare(int pixelNr) {
		int v1 = getQuery().getGrayscaleValue(pixelNr);
		int v2 = getTarget().getGrayscaleValue(pixelNr);
		origHistogram[v1]++;
		histogram[v2]++;
	}
	
	public double combine() {
		Arrays.sort(origHistogram);
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
	
	public void reset() {
		for (int i = 0; i < 256; i++) {
			origHistogram[i] = 0;
			histogram[i] = 0;
		}
	}

	public String getDescription() {
		return "OH3";
	}

}
