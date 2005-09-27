/*
 * Created on 26-sep-2005
 */
package measures;

import image.ImageData;

/**
 * @author Klaas Bosteels
 */
public abstract class MeasureBase implements Measure {

	public static boolean sameResolution(ImageData id1, ImageData id2) {
		return 
			id1.getWidth() == id2.getWidth() && 
			id1.getHeight() == id2.getHeight();
	}
	
	public String toString() {
		return getDescription();
	}
}
