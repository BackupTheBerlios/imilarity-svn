/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;


/**
 * @author Klaas Bosteels
 */
public interface StagedGrayscaleMeasure extends GrayscaleMeasure {
	
	public void compare(int pixelNr);
	public double combine();
	public void reset();

}
