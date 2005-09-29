/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;
import de.berlios.imilarity.image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class MD extends GrayscaleMeasureBase {

	/**
	 * @see de.berlios.imilarity.measures.GrayscaleMeasure#similarity(ScalableGrayscaleImage)
	 */
	public double similarity(ScalableGrayscaleImage image) {
		GrayscaleImage orig = getImage();
		if (image == null || !sameResolution(orig,image))
			return 0.0;
		int pc = orig.getWidth() * orig.getHeight();
		int max = 0;
		for (int i = 0; i < pc; i++) {
			int difference = Math.abs(orig.getGrayscaleValue(i) - image.getGrayscaleValue(i));
			if (difference > max)
				max = difference;
		}
		return 1 - (max * 1.0 / 255);
	}
	
	public String getDescription() { return "Maximum Difference"; }

}
