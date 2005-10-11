/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.Arrays;


public class OH3 extends FastGrayscaleMeasureBase {

	private int[] queryHistogram, targetHistogram;
	private int queryMax = 0, targetMax = 0;
	
	public OH3() {
		queryHistogram = new int[256];
		targetHistogram = new int[256];
		for (int i = 0; i < 256; i++) {
			queryHistogram[i] = 0;
			targetHistogram[i] = 0;
		}
	}
	
	
	public void compare(int pixelNr) {
		int v1 = getQuery().getGrayscaleValue(pixelNr);
		int v2 = getTarget().getGrayscaleValue(pixelNr);
		queryHistogram[v1]++;
		if (queryHistogram[v1] > queryMax) 
			queryMax = queryHistogram[v1];
		targetHistogram[v2]++;
		if (targetHistogram[v2] > targetMax) 
			targetMax = targetHistogram[v2];
	}
	
	public double combine() {
		Arrays.sort(queryHistogram);
		Arrays.sort(targetHistogram);
		double sum1 = 0.0, sum2 = 0.0;
		for (int i = 0; i < targetHistogram.length; i++) {
			double v1 = queryHistogram[i] * 1.0 / queryMax;
			double v2 = targetHistogram[i] * 1.0 / targetMax;
			sum1 += Math.abs(v1 - v2);
			sum2 += v1 + v2;
		}
		return 1 - (sum1 / sum2);
	}
	
	public void reset() {
		for (int i = 0; i < 256; i++) {
			queryHistogram[i] = 0;
			targetHistogram[i] = 0;
		}
		queryMax = 0;
		targetMax = 0;
	}

	public String getDescription() {
		return "OH3";
	}

}
