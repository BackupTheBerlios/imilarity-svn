/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.image;

/**
 * @author Klaas Bosteels
 */
public interface ColorImage extends Image {
	
	public int[] getColorValues(int pixelNr);
	
	public ColorImage getScaledInstance(int w, int h);
	
}
