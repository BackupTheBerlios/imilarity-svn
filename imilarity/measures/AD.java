/*
 * Created on 20-sep-2005
 */
package measures;

import image.ImageData;

/**
 * @author Klaas Bosteels
 */
public class AD extends MeasureBase {

	/**
	 * @see measures.Measure#similarity(image.ImageData, image.ImageData)
	 */
	public double similarity(ImageData id1, ImageData id2) {
		if (id1 == null || id2 == null || !sameResolution(id1,id2))
			return 0.0;
		int pc = id1.getPixelCount();
		long sum = 0;
		for (int i = 0; i < pc; i++)
			sum += Math.abs(id1.getGrayscaleValue(i) - id2.getGrayscaleValue(i));
		return 1 - ((sum / pc) * 1.0 / 255);
	}

	public String getDescription() {
		return "Average Difference";
	}
}
