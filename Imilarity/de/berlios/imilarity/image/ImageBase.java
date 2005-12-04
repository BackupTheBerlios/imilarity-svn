/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;

public abstract class ImageBase implements Image {
	
	public Color getColor(int pixelNr) {
		int w = getWidth();
		return getColor(pixelNr % w, pixelNr / w);
	}

}
