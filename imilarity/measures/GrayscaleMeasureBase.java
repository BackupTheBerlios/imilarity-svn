/*
 * Created on 26-sep-2005
 */
package measures;

import image.GrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public abstract class GrayscaleMeasureBase implements GrayscaleMeasure {

	public static boolean sameResolution(GrayscaleImage gi1, GrayscaleImage gi2) {
		return 
			gi1.getWidth() == gi2.getWidth() && 
			gi1.getHeight() == gi2.getHeight();
	}
	
	public String toString() {
		return getDescription();
	}
}
