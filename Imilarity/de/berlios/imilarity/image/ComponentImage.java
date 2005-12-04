/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;

public class ComponentImage extends ImageBase {
	
	private Image image;
	private int componentNr;
	
	public ComponentImage(Image image, int componentNr) {
		if (image == null)
			throw new NullPointerException("colorImage == null");
		this.image = image;
		if (componentNr < 0 || componentNr > image.getColorComponentsCount())
			throw new IllegalArgumentException("0 <= componentNr <= 2 not satisfied");
		this.componentNr = componentNr;
	}
	
	public int getColorComponentsCount() {
		return 1;
	}
	
	public Color getColor(int x, int y) {
		return new Color(image.getColor(x, y).getComponents()[componentNr]);
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public Image getScaledInstance(int w, int h) {
		return new ComponentImage(image.getScaledInstance(w,h), componentNr);
	}

}
