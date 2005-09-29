/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ColorImage;


/**
 * @author Klaas Bosteels
 */
public abstract class ColorMeasureBase implements ColorMeasure {

	private ColorImage image;

	public void setImage(ColorImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}
	
	public ColorImage getImage() {
		return image;
	}
	
	public String toString() {
		return getDescription();
	}

}
