/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public abstract class GrayscaleMeasureBase implements GrayscaleMeasure {
	
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
