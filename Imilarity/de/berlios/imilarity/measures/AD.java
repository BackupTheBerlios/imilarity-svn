/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class AD extends FastGrayscaleMeasureBase {

	private double sum = 0.0;
	
	public void compare(int v1, int v2) {
		sum += Math.abs(v1 - v2);
	}
	
	public double combine() {
		GrayscaleImage orig = getImage();
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
