/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;



/**
 * @author Klaas Bosteels
 */
public class MD extends StagedGrayscaleMeasureBase {

	private double max = 0.0;
	
	public void compare(int pixelNr) {
		int v1 = getQuery().getGrayscaleValue(pixelNr);
		int v2 = getTarget().getGrayscaleValue(pixelNr);
		int difference = Math.abs(v1 - v2);
		if (difference > max)
			max = difference;
	}
	
	public double combine() {
		return 1 - (max * 1.0 / 255);
	}
	
	public void reset() {
		max = 0.0;
	}
	
	public String getDescription() { return "Maximum Difference"; }

}
