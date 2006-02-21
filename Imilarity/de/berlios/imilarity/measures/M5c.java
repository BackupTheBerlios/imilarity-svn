package de.berlios.imilarity.measures;

public class M5c extends FuzzyMeasureBase {

	public double getSimilarity() {
		int count = getQuery().getElementsCount();
		double queryAbs = getQuery().abs();
		double targetAbs = getTarget().abs();
		double d = count - Math.min(queryAbs,targetAbs);
		if (d == 0)
			return 0;
		else	
			return (count - Math.max(queryAbs,targetAbs)) / d; 
	}

	public String getDescription() {
		return "M5c";
	}

}
