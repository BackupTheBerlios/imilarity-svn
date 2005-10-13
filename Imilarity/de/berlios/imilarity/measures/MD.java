/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;



/**
 * @author Klaas Bosteels
 */
public class MD extends GrayscaleStagedImageMeasureBase {

	private double max = 0.0;
	
	public void compare(int pixelNr) {
		int v1 = (int) (getQuery().getColor(pixelNr).getComponents()[0]*255);
		int v2 = (int) (getTarget().getColor(pixelNr).getComponents()[0]*255);
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
