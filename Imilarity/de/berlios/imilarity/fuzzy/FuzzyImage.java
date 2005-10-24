/**
 * 
 */
package de.berlios.imilarity.fuzzy;

import de.berlios.imilarity.image.Image;

/**
 * @author klbostee
 *
 */
public class FuzzyImage extends FuzzySetBase {

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
		return new ColorMembership
			(image.getColor(element % image.getWidth(), element / image.getWidth()));
	}

	public FuzzySet intersection(FuzzySet set) {
		int pc = getElementsCount();
		if (set.getElementsCount() != pc)
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet();
		for (int i = 0; i < pc; i++) {
			Membership m = getMembership(i);
			result.addMembership(new ColorMembership(m.and(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		int pc = getElementsCount();
		if (set.getElementsCount() != pc)
			throw new IllegalArgumentException("not the same elements count");
		ArrayFuzzySet result = new ArrayFuzzySet();
		for (int i = 0; i < pc; i++) {
			Membership m = getMembership(i);
			result.addMembership(new ColorMembership(m.or(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet complement() {
		int pc = getElementsCount();
		ArrayFuzzySet result = new ArrayFuzzySet();
		for (int i = 0; i < pc; i++) {
			Membership m = getMembership(i);
			result.addMembership(new ColorMembership(m.complement().getComponents()));
		}
		return result;
	}

}
