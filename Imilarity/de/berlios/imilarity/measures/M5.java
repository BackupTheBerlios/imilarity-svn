package de.berlios.imilarity.measures;

public class M5 extends FuzzyMeasureBase {

	public double getSimilarity() {
		double queryAbs = getQuery().abs();
		double targetAbs = getTarget().abs();
		return Math.min(queryAbs,targetAbs) / Math.max(queryAbs,targetAbs);
	}

	public String getDescription() {
		return "M5";
	}

}
