/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ComponentImageAdapter;
import de.berlios.imilarity.image.ColorImage;

public class CombinedColorMeasure extends ColorMeasureBase {
	
	private GrayscaleMeasure[] measures;
	
	public CombinedColorMeasure(GrayscaleMeasure[] measures) {
		if (measures == null)
			throw new NullPointerException("measures == null");
		this.measures = measures;
	}
	
	public void setImage(ColorImage image) {
		super.setImage(image);
		for (int i = 0; i < 3; i++)
			measures[i].setImage(new ComponentImageAdapter(image, i));
	}
	
	public double similarity(ColorImage image) {
		double sum = 0.0;
		for (int i = 0; i < 3; i++)
			sum += measures[i].similarity(new ComponentImageAdapter(image, i));
		return sum / 3;
	}

	public String getDescription() {
		return "Combined " + measures[0].getDescription() + ", " +
			measures[1].getDescription() + " and " + measures[2].getDescription();
	}

}
