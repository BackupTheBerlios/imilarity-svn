/*
 * Created on 28-sep-2005
 */
package measures;

import image.ScalableColorImage;


/**
 * @author Klaas Bosteels
 */
public interface ColorMeasure {
	
	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen ci1 en ci2 weergeeft.
	 */
	public double similarity(ScalableColorImage ci1, ScalableColorImage ci2);
	
	public String getDescription();
}