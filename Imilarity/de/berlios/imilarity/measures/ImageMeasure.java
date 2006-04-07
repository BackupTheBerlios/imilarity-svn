/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;

public interface ImageMeasure extends Measure {
	
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
	
}
