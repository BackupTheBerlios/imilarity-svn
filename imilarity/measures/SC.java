/*
 * Created on 21-sep-2005
 */
package measures;

import image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class SC extends GrayscaleMeasureBase {

	/**
	 * @see measures.GrayscaleMeasure#similarity(image.ImageData, image.ImageData)
	 */
	public double similarity(ScalableGrayscaleImage gi1, ScalableGrayscaleImage gi2) {
		if (gi1 == null || gi2 == null || !sameResolution(gi1,gi2))
			return 0.0;
		int pc = gi1.getWidth() * gi1.getHeight();
		long sum1 = 0, sum2 = 0;
		for (int i = 0; i < pc; i++) {
			sum1 += gi1.getGrayscaleValue(i) * gi1.getGrayscaleValue(i);
			sum2 += gi2.getGrayscaleValue(i) * gi2.getGrayscaleValue(i);
		}
		return 1 - Math.abs(1 - (sum1 * 1.0 / sum2));
	}
	
	public String getDescription() {
		return "Structural Content";
	}

}
