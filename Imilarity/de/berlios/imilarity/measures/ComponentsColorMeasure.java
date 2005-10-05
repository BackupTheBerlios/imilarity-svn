/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ComponentGrayscaleImage;
import de.berlios.imilarity.image.ColorImage;
import de.berlios.imilarity.image.GrayscaleImage;

public class ComponentsColorMeasure extends ColorMeasureBase {
	
	private GrayscaleMeasure measure;
	private GrayscaleImage[] compImages;
	
	public ComponentsColorMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		compImages = new GrayscaleImage[3];
	}
	
	public void setImage(ColorImage image) {
		super.setImage(image);
		for (int i = 0; i < 3; i++)
			compImages[i] = new ComponentGrayscaleImage(image, i);
	}
	
	public double similarity(ColorImage image) {
		double sum = 0.0;
		for (int i = 0; i < 3; i++) {
			measure.setImage(compImages[i]);
			sum += measure.similarity(new ComponentGrayscaleImage(image, i));
		}
		return sum / 3;
	}

	public String getDescription() {
		return "Combined " + measure.getDescription();
	}

}
