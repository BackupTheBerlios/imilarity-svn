/*
 * Created on 20-sep-2005
 */
package measures;

import image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class MD extends GrayscaleMeasureBase {

	/**
	 * @see measures.GrayscaleMeasure#similarity(image.ImageData, image.ImageData)
	 */
	public double similarity(GrayscaleImage gi1, GrayscaleImage gi2) {
		if (gi1 == null || gi2 == null || !sameResolution(gi1,gi2))
			return 0.0;
		int pc = gi1.getWidth() * gi1.getHeight();
		int max = 0;
		for (int i = 0; i < pc; i++) {
			int difference = Math.abs(gi1.getGrayscaleValue(i) - gi2.getGrayscaleValue(i));
			if (difference > max)
				max = difference;
		}
		return 1 - (max * 1.0 / 255);
	}
	
	public String getDescription() { return "Maximum Difference"; }

}
