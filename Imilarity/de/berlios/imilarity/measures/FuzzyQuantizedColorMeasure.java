package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ColorImage;

public class FuzzyQuantizedColorMeasure extends ColorMeasureBase {

	public double getSimilarity() {
		ColorImage query = getQuery();
		ColorImage target = getTarget();
		return 0;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
