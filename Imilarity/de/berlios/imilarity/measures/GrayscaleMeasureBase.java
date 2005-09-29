/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public abstract class GrayscaleMeasureBase implements GrayscaleMeasure {

	public static boolean sameResolution(GrayscaleImage gi1, GrayscaleImage gi2) {
		return 
			gi1.getWidth() == gi2.getWidth() && 
			gi1.getHeight() == gi2.getHeight();
	}

	private GrayscaleImage image;
	
	public void setImage(GrayscaleImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}
	
	public GrayscaleImage getImage() {
		return image;
	}
	
	public String toString() {
		return getDescription();
	}
}
