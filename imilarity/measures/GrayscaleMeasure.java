/*
 * Created on 20-sep-2005
 */
package measures;

import image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public interface GrayscaleMeasure {

	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen gi1 en gi2 weergeeft.
	 */
	public double similarity(ScalableGrayscaleImage gi1, ScalableGrayscaleImage gi2);
	
	public String getDescription();
	
}
