/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;
import de.berlios.imilarity.image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
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
	public double similarity(ScalableGrayscaleImage image);
	
	public String getDescription();
	
}
