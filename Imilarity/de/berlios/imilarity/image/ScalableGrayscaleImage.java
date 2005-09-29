/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.image;

/**
 * @author Klaas Bosteels
 */
public interface ScalableGrayscaleImage extends GrayscaleImage {

	public ScalableGrayscaleImage getScaledInstance(int w, int h);
	
}
