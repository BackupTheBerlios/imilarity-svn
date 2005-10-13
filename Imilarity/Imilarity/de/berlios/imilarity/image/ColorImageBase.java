/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.image;

public abstract class ColorImageBase implements ColorImage {
	
	public int[] getColorValues(int pixelNr) {
		int w = getWidth();
		return getColorValues(pixelNr % w, pixelNr / w);
	}

}
