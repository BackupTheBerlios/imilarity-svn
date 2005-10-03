/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ColorImage;


/**
 * @author Klaas Bosteels
 */
public interface ColorMeasure {
	
	/**
	 * @param image beeld waarmee vergeleken moet worden
	 */
	public void setImage(ColorImage image);
	
	public ColorImage getImage();
	
	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen 'image' en het ingestelde beeld weergeeft.
	 */
	public double similarity(ColorImage image);
	
	public String getDescription();
}
