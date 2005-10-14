/**
 * 
 */
package de.berlios.imilarity.fuzzy;

import de.berlios.imilarity.image.Image;

/**
 * @author klbostee
 *
 */
public class FuzzyImage implements FuzzySet {

	private Image image;
	
	public FuzzyImage(Image image) {
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
		return image.getColor(element % image.getWidth(), element / image.getWidth());
	}

}
