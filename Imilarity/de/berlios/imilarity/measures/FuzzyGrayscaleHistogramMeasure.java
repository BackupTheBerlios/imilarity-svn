/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.Arrays;

import de.berlios.imilarity.fuzzy.FuzzyGrayscaleHistogram;


public class FuzzyGrayscaleHistogramMeasure extends FastGrayscaleMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	
	private int[] queryHistogram, targetHistogram;
	private int queryMax = 0, targetMax = 0;
	
	public FuzzyGrayscaleHistogramMeasure(FuzzyMeasure fuzzyMeasure) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		this.fuzzyMeasure = fuzzyMeasure;
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
		
		fuzzyMeasure.setQuery(new FuzzyGrayscaleHistogram(queryHistogram, queryMax));
		fuzzyMeasure.setTarget(new FuzzyGrayscaleHistogram(targetHistogram, targetMax));
		return fuzzyMeasure.getSimilarity();
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
		return "Fuzzy Grayscale Histogram using " + fuzzyMeasure.getDescription();
	}

}
