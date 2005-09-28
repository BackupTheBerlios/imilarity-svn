/*
 * Created on 20-sep-2005
 */
package measures;

import image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class AD extends GrayscaleMeasureBase {

	/**
	 * @see measures.GrayscaleMeasure#similarity(image.ImageData, image.ImageData)
	 */
	public double similarity(GrayscaleImage gi1, GrayscaleImage gi2) {
		if (gi1 == null || gi2 == null || !sameResolution(gi1,gi2))
			return 0.0;
		int pc = gi1.getWidth() * gi2.getHeight();
		long sum = 0;
		for (int i = 0; i < pc; i++)
			sum += Math.abs(gi1.getGrayscaleValue(i) - gi2.getGrayscaleValue(i));
		return 1 - ((sum / pc) * 1.0 / 255);
	}

	public String getDescription() {
		return "Average Difference";
	}
}
