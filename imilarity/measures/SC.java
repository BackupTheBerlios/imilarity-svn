/*
 * Created on 21-sep-2005
 */
package measures;

import image.ImageData;

/**
 * @author Klaas Bosteels
 */
public class SC extends MeasureBase {

	/**
	 * @see measures.Measure#similarity(image.ImageData, image.ImageData)
	 */
	public double similarity(ImageData id1, ImageData id2) {
		if (id1 == null || id2 == null || !sameResolution(id1,id2))
			return 0.0;
		int pc = id1.getPixelCount();
		long sum1 = 0, sum2 = 0;
		for (int i = 0; i < pc; i++) {
			sum1 += id1.getGrayscaleValue(i) * id1.getGrayscaleValue(i);
			sum2 += id2.getGrayscaleValue(i) * id2.getGrayscaleValue(i);
		}
		return 1 - Math.abs(1 - (sum1 * 1.0 / sum2));
	}
	
	public String getDescription() {
		return "Structural Content";
	}

}
