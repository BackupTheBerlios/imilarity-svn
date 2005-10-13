/**
 * 
 */
package de.berlios.imilarity.fuzzy;

import de.berlios.imilarity.image.GrayscaleImage;

/**
 * @author klbostee
 *
 */
public class FuzzyGrayscaleImage implements FuzzySet {

	private GrayscaleImage image;
	
	public FuzzyGrayscaleImage(GrayscaleImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}
	
	/**
	 * @see de.berlios.imilarity.fuzzy.FuzzySet#getElementsCount()
	 */
	public int getElementsCount() {
		return image.getHeight() * image.getWidth();
	}

	/**
	 * @see de.berlios.imilarity.fuzzy.FuzzySet#getMembership(int)
	 */
	public Membership getMembership(int element) {
		return new ScalarMembership(image.getGrayscaleValue
				(element % image.getWidth(), element / image.getWidth()) * 1.0 / 255);
	}

}
