/**
 * 
 */
package de.berlios.imilarity.fuzzy;

import de.berlios.imilarity.image.GrayscaleImage;

/**
 * @author klbostee
 *
 */
public class FuzzyImage implements FuzzySet {

	private GrayscaleImage image;
	
	public FuzzyImage(GrayscaleImage image) {
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
	public double getMembership(int element) {
		return image.getGrayscaleValue(element % image.getWidth(), element / image.getWidth());
	}

}
