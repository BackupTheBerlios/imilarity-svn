/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public abstract class GrayscaleMeasureBase implements GrayscaleMeasure {

	public static boolean sameResolution(ScalableGrayscaleImage gi1, ScalableGrayscaleImage gi2) {
		return 
			gi1.getWidth() == gi2.getWidth() && 
			gi1.getHeight() == gi2.getHeight();
	}

	private ScalableGrayscaleImage image;
	
	public void setImage(ScalableGrayscaleImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}
	
	public ScalableGrayscaleImage getImage() {
		return image;
	}
	
	public String toString() {
		return getDescription();
	}
}
