/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public abstract class FastGrayscaleMeasureBase extends GrayscaleMeasureBase 
	implements FastGrayscaleMeasure  {

	private static boolean sameResolution(GrayscaleImage gi1, GrayscaleImage gi2) {
		return 
			gi1.getWidth() == gi2.getWidth() && 
			gi1.getHeight() == gi2.getHeight();
	}
	
	public double similarity(GrayscaleImage image) {
		GrayscaleImage orig = getImage();
		if (orig == null || image == null || !sameResolution(orig, image))
			return 0.0;
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++)
			compare(orig.getGrayscaleValue(i), image.getGrayscaleValue(i));
		double result = combine();
		reset();
		return result;
	}
	
}
