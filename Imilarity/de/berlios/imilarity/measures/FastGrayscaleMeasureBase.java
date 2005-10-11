/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public abstract class FastGrayscaleMeasureBase extends GrayscaleMeasureBase 
	implements FastGrayscaleMeasure  {

	protected static boolean sameResolution(GrayscaleImage gi1, GrayscaleImage gi2) {
		return 
			gi1.getWidth() == gi2.getWidth() && 
			gi1.getHeight() == gi2.getHeight();
	}
	
	public double getSimilarity() {
		GrayscaleImage query = getQuery();
		GrayscaleImage target = getTarget();
		if (query == null || target == null || !sameResolution(query, target))
			return 0.0;
		int pc = target.getWidth() * target.getHeight();
		for (int i = 0; i < pc; i++)
			compare(i);
		double result = combine();
		reset();
		return result;
	}
	
}
