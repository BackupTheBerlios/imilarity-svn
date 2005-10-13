/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;


/**
 * @author Klaas Bosteels
 */
public interface StagedImageMeasure extends ImageMeasure {
	
	void compare(int pixelNr);
	double combine();
	void reset();

}
