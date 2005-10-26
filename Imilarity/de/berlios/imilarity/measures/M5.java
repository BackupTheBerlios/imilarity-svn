package de.berlios.imilarity.measures;

public class M5 extends FuzzyMeasureBase {

	public double getSimilarity() {
		double queryAbs = getQuery().abs();
		double targetAbs = getTarget().abs();
		double d = Math.max(queryAbs,targetAbs);
		if (d == 0)
			return 0;
		else	
			return Math.min(queryAbs,targetAbs) / d; 
	}

	public String getDescription() {
		return "M5";
	}

}
