/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.Arrays;

import de.berlios.imilarity.fuzzy.FuzzyGrayscaleHistogram;
import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.image.GrayscaleImage;


public class FuzzyHistogramImageMeasure extends GrayscaleStagedImageMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	
	private int[] queryHistogram, targetHistogram;
	private int queryMax = 0, targetMax = 0, targetPc = 0, queryPc = 0; // pc = pixelcount
	
	public FuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure) {
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
	
	public void setQuery(GrayscaleImage query) {
		super.setQuery(query);
		queryPc = query.getWidth() * query.getHeight();
	}
	
	public void setTarget(GrayscaleImage target) {
		super.setTarget(target);
		targetPc = target.getWidth() * target.getHeight();
	}
	
	public void compare(int pixelNr) {
		if (pixelNr < queryPc) {
			int v1 = (int) (getQuery().getColor(pixelNr).getComponents()[0]*255);
			queryHistogram[v1]++;
			if (queryHistogram[v1] > queryMax) 
				queryMax = queryHistogram[v1];
		}
		if (pixelNr < targetPc) {
			int v2 = (int) (getTarget().getColor(pixelNr).getComponents()[0]*255);
			targetHistogram[v2]++;
			if (targetHistogram[v2] > targetMax) 
				targetMax = targetHistogram[v2];
		}
	}
	
	public double combine() {		
		Arrays.sort(queryHistogram);
		Arrays.sort(targetHistogram);
		
		fuzzyMeasure.setQuery(getQueryHistogram());
		fuzzyMeasure.setTarget(getTargetHistogram());
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
	
	
	public FuzzySet getQueryHistogram() {
		return new FuzzyGrayscaleHistogram(queryHistogram, queryMax);
	}
	
	public int getQueryMax() {
		return queryMax;
	}
	
	public int getQueryMin() {
		int min = queryPc;
		for (int i = 0; i < queryHistogram.length; i++)
			if (queryHistogram[i] < min)
				min = queryHistogram[i];
		return min;
	}
	
	public FuzzySet getTargetHistogram() {
		return new FuzzyGrayscaleHistogram(targetHistogram, targetMax);
	}
	
	public int getTargetMax() {
		return targetMax;
	}
	
	public int getTargetMin() {
		int min = targetPc;
		for (int i = 0; i < targetHistogram.length; i++)
			if (targetHistogram[i] < min)
				min = targetHistogram[i];
		return min;
	}
	
	public FuzzyMeasure getFuzzyMeasure() {
		return fuzzyMeasure;
	}

}
