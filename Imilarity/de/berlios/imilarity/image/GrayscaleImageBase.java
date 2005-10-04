/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.image;

public abstract class GrayscaleImageBase implements GrayscaleImage {
	
	public int[] getHistogram() {
		int[] histogram = new int[256];
		int pc = getWidth() * getHeight();
		for (int i = 0; i < pc; i++)
			histogram[getGrayscaleValue(i)]++;
		return histogram;
	}
	
}
