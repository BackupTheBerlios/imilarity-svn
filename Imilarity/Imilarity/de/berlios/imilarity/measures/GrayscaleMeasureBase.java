/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;

public abstract class GrayscaleMeasureBase implements GrayscaleMeasure {
	
	private GrayscaleImage query, target;
	
	public void setQuery(GrayscaleImage query) {
		if (query == null)
			throw new NullPointerException("query == null");
		this.query = query;
	}
	
	public GrayscaleImage getQuery() {
		return query;
	}
	
	public void setTarget(GrayscaleImage target) {
		if (target == null)
			throw new NullPointerException("target == null");
		this.target = target;
	}
	
	public GrayscaleImage getTarget() {
		return target;
	}
	
	
	public String toString() {
		return getDescription();
	}
}
