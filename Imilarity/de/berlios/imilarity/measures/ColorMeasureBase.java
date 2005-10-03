/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ScalableColorImage;


/**
 * @author Klaas Bosteels
 */
public abstract class ColorMeasureBase implements ColorMeasure {

	private ScalableColorImage image;

	public void setImage(ScalableColorImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}
	
	public ScalableColorImage getImage() {
		return image;
	}
	
	public String toString() {
		return getDescription();
	}

}
