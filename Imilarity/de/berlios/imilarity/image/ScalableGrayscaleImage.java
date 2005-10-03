/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.image;

/**
 * @author Klaas Bosteels
 */
public interface ScalableGrayscaleImage extends Image {

	public int getGrayscaleValue(int pixelNr);
	
	public ScalableGrayscaleImage getScaledInstance(int w, int h);
	
}
