/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;


/**
 * @author Klaas Bosteels
 */
public class AD extends GrayscaleStagedImageMeasureBase {

	private double sum = 0.0;
	
	
	public void compare(int pixelNr) {
		int v1 = (int) (getQuery().getColor(pixelNr).getComponents()[0]*255);
		int v2 = (int) (getTarget().getColor(pixelNr).getComponents()[0]*255);
		sum += Math.abs(v1 - v2);
	}
	
	public double combine() {
		Image orig = getQuery();
		int pc = orig.getWidth() * orig.getHeight();
		return 1 - ((sum / pc) * 1.0 / 255);
	}
	
	public void reset() {
		sum = 0.0;
	}

	public String getDescription() {
		return "Average Difference";
	}
}
