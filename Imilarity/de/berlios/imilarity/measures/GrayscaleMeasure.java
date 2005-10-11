/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public interface GrayscaleMeasure {
	
	/**
	 * @param query beeld waarmee de doelbeelden vergeleken moeten worden
	 */
	public void setQuery(GrayscaleImage query);
	public GrayscaleImage getQuery();
	
	/**
	 * @param target het huidige doelbeeld
	 */
	public void setTarget(GrayscaleImage target);
	public GrayscaleImage getTarget();
	
	
	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen het query- en target-beeld weergeeft.
	 */
	public double getSimilarity();
	
	
	public String getDescription();
	
}
