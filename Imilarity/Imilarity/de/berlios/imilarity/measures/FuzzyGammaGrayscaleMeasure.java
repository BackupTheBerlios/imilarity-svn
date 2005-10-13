package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.FuzzyArray;
import de.berlios.imilarity.fuzzy.FuzzySet;

public class FuzzyGammaGrayscaleMeasure extends FuzzyGrayscaleHistogramMeasure {	

	public FuzzyGammaGrayscaleMeasure(FuzzyMeasure fuzzyMeasure) {
		super(fuzzyMeasure);
	}

	public double combine() {
		double[] targetMemberships = new double[256], queryMemberships = new double[256];
		FuzzySet targetHist = getTargetHistogram(), queryHist = getQueryHistogram();
		double c = 1.0 / (getTargetMax() - getTargetMin()); 
		for (int i = 0; i < targetMemberships.length; i++) {
			targetMemberships[i] = Math.exp(-c * Math.abs
					(targetHist.getMembership(i) - queryHist.getMembership(i)));
			queryMemberships[i] = 1.0;
		}
		FuzzyMeasure fuzzyMeasure = getFuzzyMeasure();
		fuzzyMeasure.setQuery(new FuzzyArray(queryMemberships));
		fuzzyMeasure.setTarget(new FuzzyArray(targetMemberships));
		return fuzzyMeasure.getSimilarity();
	}

	public String getDescription() {
		return "Fuzzy Gamma Grayscale using " + getFuzzyMeasure().getDescription();
	}

}
