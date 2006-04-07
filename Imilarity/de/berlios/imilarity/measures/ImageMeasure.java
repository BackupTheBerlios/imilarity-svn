/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.util.Describable;

public interface ImageMeasure extends Describable {
	
	/**
	 * @param query beeld waarmee de doelbeelden vergeleken moeten worden
	 */
	public void setQuery(Image query);
	public Image getQuery();
	
	/**
	 * @param target het huidige doelbeeld
	 */
	public void setTarget(Image target);
	public Image getTarget();
	
	
	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen het query- en target-beeld weergeeft.
	 */
	public double getSimilarity();
	
}
