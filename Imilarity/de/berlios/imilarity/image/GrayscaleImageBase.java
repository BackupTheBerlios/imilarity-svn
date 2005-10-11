/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.image;

public abstract class GrayscaleImageBase implements GrayscaleImage {
	
	public int getGrayscaleValue(int pixelNr) {
		int w = getWidth();
		if ((pixelNr / w) > w)
			System.out.println("Error: pixelNr = " + pixelNr);
		return getGrayscaleValue(pixelNr % w, pixelNr / w);
	}
	
}
