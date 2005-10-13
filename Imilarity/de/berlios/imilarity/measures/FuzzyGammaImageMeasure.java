package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.HashedFuzzySet;
import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.Membership;

public class FuzzyGammaImageMeasure extends FuzzyHistogramImageMeasure {	

	public FuzzyGammaImageMeasure(FuzzyMeasure fuzzyMeasure) {
		super(fuzzyMeasure);
	}

	public double combine() {
		double[] targetMemberships = new double[256], queryMemberships = new double[256];
		FuzzySet targetHist = getTargetHistogram(), queryHist = getQueryHistogram();
		double c = 1.0 / (getTargetMax() - getTargetMin()); 
		for (int i = 0; i < targetMemberships.length; i++) {
			targetMemberships[i] = Math.exp(-c * 
					(targetHist.getMembership(i).minus(queryHist.getMembership(i))).abs());
			queryMemberships[i] = 1.0;
		}
		FuzzyMeasure fuzzyMeasure = getFuzzyMeasure();
		
		HashedFuzzySet map1 = new HashedFuzzySet(), map2 = new HashedFuzzySet();
		for (int i = 0; i < 256; i++) {
			map1.addMembership(new Membership(queryMemberships[i]));
			map2.addMembership(new Membership(targetMemberships[i]));
		}
		fuzzyMeasure.setQuery(map1);
		fuzzyMeasure.setTarget(map2);
		return fuzzyMeasure.getSimilarity();
	}

	public String getDescription() {
		return "Fuzzy Gamma Grayscale using " + getFuzzyMeasure().getDescription();
	}

}
