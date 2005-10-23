/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;

public abstract class ImageMeasureBase implements ImageMeasure {
	
	private Image query, target;
	
	public void setQuery(Image query) {
		if (query == null)
			throw new NullPointerException("query == null");
		this.query = query;
	}
	
	public Image getQuery() {
		return query;
	}
	
	public void setTarget(Image target) {
		if (target == null)
			throw new NullPointerException("target == null");
		this.target = target;
	}
	
	public Image getTarget() {
		return target;
	}
	
	
	public String toString() {
		return getDescription();
	}
	
	
	public static boolean sameResolution(Image gi1, Image gi2) {
		return 
			gi1.getWidth() == gi2.getWidth() && 
			gi1.getHeight() == gi2.getHeight();
	}
}
