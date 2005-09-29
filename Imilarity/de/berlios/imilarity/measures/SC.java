/*
 * Created on 21-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;
import de.berlios.imilarity.image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class SC extends GrayscaleMeasureBase {

	/**
	 * @see de.berlios.imilarity.measures.GrayscaleMeasure#similarity(ScalableGrayscaleImage)
	 */
	public double similarity(ScalableGrayscaleImage image) {
		GrayscaleImage orig = getImage();
		if (orig == null) System.out.println("orig == null");
		if (orig == null || image == null || !sameResolution(orig,image))
			return 0.0;
		int pc = orig.getWidth() * orig.getHeight();
		long sum1 = 0, sum2 = 0;
		for (int i = 0; i < pc; i++) {
			sum1 += orig.getGrayscaleValue(i) * orig.getGrayscaleValue(i);
			sum2 += image.getGrayscaleValue(i) * image.getGrayscaleValue(i);
		}
		return 1 - Math.abs(1 - (sum1 * 1.0 / sum2));
	}
	
	public String getDescription() {
		return "Structural Content";
	}

}
