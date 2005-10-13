/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.image;

public abstract class ColorImageBase implements ColorImage {
	
	public Color getColor(int pixelNr) {
		int w = getWidth();
		return getColor(pixelNr % w, pixelNr / w);
	}

}
