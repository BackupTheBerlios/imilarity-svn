/*
 * Created on 28-sep-2005
 */
package measures;

import image.ColorImage;
import image.GrayscaleImage;
import image.GrayscaleImageAdapter;

/**
 * @author Klaas Bosteels
 */
public class GrayscaledColorMeasure implements ColorMeasure {

	private GrayscaleMeasure measure;
	
	public GrayscaledColorMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	/**
	 * @see measures.ColorMeasure#similarity(image.ColorImage, image.ColorImage)
	 */
	public double similarity(ColorImage ci1, ColorImage ci2) {
		GrayscaleImage gi1 = new GrayscaleImageAdapter(ci1);
		GrayscaleImage gi2 = new GrayscaleImageAdapter(ci2);
		return measure.similarity(gi1, gi2);
	}

	/**
	 * @see measures.ColorMeasure#getDescription()
	 */
	public String getDescription() {
		return "Grayscaled " + measure.getDescription();
	}

}
