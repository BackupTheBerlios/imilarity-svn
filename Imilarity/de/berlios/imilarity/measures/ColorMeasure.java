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
	 * @param query beeld waarmee vergeleken moet worden
	 */
	public void setQuery(ColorImage query);
	public ColorImage getQuery();
	
	/**
	 * @param target het huidige doelbeeld
	 */
	public void setTarget(ColorImage target);
	public ColorImage getTarget();
	
	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen het ingestelde query- en target-beeld weergeeft.
	 */
	public double getSimilarity();
	
	public String getDescription();
}
