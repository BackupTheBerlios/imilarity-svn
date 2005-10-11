/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ColorImage;


/**
 * @author Klaas Bosteels
 */
public abstract class ColorMeasureBase implements ColorMeasure {

	private ColorImage query, target;

	public void setQuery(ColorImage query) {
		if (query == null)
			throw new NullPointerException("query == null");
		this.query = query;
	}
	
	public ColorImage getQuery() {
		return query;
	}
	
	public void setTarget(ColorImage target) {
		if (target == null)
			throw new NullPointerException("target == null");
		this.target = target;
	}
	
	public ColorImage getTarget() {
		return target;
	}
	
	public String toString() {
		return getDescription();
	}

}
