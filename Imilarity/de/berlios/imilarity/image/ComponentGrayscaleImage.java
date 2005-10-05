/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.image;

public class ComponentGrayscaleImage extends GrayscaleImageBase {
	
	private ColorImage colorImage;
	private int componentNr;
	
	public ComponentGrayscaleImage(ColorImage colorImage, int componentNr) {
		if (colorImage == null)
			throw new NullPointerException("colorImage == null");
		this.colorImage = colorImage;
		if (componentNr < 0 || componentNr > 2)
			throw new IllegalArgumentException("0 <= componentNr <= 2 not satisfied");
		this.componentNr = componentNr;
	}
	
	public int getGrayscaleValue(int x, int y) {
		return colorImage.getColorValues(x, y)[componentNr];
	}

	public int getWidth() {
		return colorImage.getWidth();
	}

	public int getHeight() {
		return colorImage.getHeight();
	}

	public GrayscaleImage getScaledInstance(int w, int h) {
		return new ComponentGrayscaleImage(colorImage.getScaledInstance(w,h), componentNr);
	}

}
