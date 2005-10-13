/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public abstract class StagedGrayscaleMeasureBase extends GrayscaleMeasureBase 
	implements StagedGrayscaleMeasure  {

	public static boolean sameResolution(GrayscaleImage gi1, GrayscaleImage gi2) {
		return 
			gi1.getWidth() == gi2.getWidth() && 
			gi1.getHeight() == gi2.getHeight();
	}
	
	public double getSimilarity() {
		GrayscaleImage query = getQuery();
		GrayscaleImage target = getTarget();
		if (query == null || target == null)
			return 0.0;
		int pc1 = query.getWidth() * query.getHeight();
		int pc2 = target.getWidth() * target.getHeight();
		int pc = pc1 > pc2 ? pc1 : pc2;
		for (int i = 0; i < pc; i++)
			compare(i);
		double result = combine();
		reset();
		return result;
	}
	
}
