/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public interface GrayscaleMeasure {
	
	/**
	 * @param image beeld waarmee vergeleken moet worden
	 */
	public void setImage(GrayscaleImage image);
	
	public GrayscaleImage getImage();
	
	
	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * van 'image' met het ingestelde beeld weergeeft.
	 */
	public double similarity(GrayscaleImage image);
	
	public String getDescription();
	
}
