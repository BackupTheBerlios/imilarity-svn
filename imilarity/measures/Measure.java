/*
 * Created on 20-sep-2005
 */
package measures;

import image.ImageData;


/**
 * @author Klaas Bosteels
 */
public interface Measure {

	/**
	 * Geeft een getal in het interval [0,1] terug dat de graad van similariteit
	 * tussen id1 en id2 weergeeft.
	 */
	public double similarity(ImageData id1, ImageData id2);
	
	public String getDescription();
	
}
