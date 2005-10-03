/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.image;

public class ComponentImageAdapter implements ScalableGrayscaleImage {
	
	private ScalableColorImage colorImage;
	private int componentNr;
	
	public ComponentImageAdapter(ScalableColorImage colorImage, int componentNr) {
		if (colorImage == null)
			throw new NullPointerException("colorImage == null");
		this.colorImage = colorImage;
		if (componentNr < 0 || componentNr > 2)
			throw new IllegalArgumentException("0 <= componentNr <= 2 not satisfied");
		this.componentNr = componentNr;
	}
	
	public int getGrayscaleValue(int pixelNr) {
		return colorImage.getColorValues(pixelNr)[componentNr];
	}

	public int getWidth() {
		return colorImage.getWidth();
	}

	public int getHeight() {
		return colorImage.getHeight();
	}

	public ScalableGrayscaleImage getScaledInstance(int w, int h) {
		return new ComponentImageAdapter(colorImage.getScaledInstance(w,h), componentNr);
	}

}
