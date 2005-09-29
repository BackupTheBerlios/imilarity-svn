/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.image;

/**
 * @author Klaas Bosteels
 */
public class ColorImageAdapter implements ScalableColorImage {

	private ImageData imageData;
	
	public ColorImageAdapter(ImageData id) {
		if (id == null)
			throw new NullPointerException("id == null");
		imageData = id;
	}
	
	
	/**
	 * @see de.berlios.imilarity.image.ScalableColorImage#getColorValues(int)
	 */
	public int[] getColorValues(int pixelNr) {
		return imageData.getRgb(pixelNr);
	}

	/**
	 * @see de.berlios.imilarity.image.ScalableColorImage#getScaledInstance(int, int)
	 */
	public ScalableColorImage getScaledInstance(int w, int h) {
		return new ColorImageAdapter(imageData.getScaledInstance(w,h));
	}

	/**
	 * @see de.berlios.imilarity.image.Image#getWidth()
	 */
	public int getWidth() {
		return imageData.getWidth();
	}

	/**
	 * @see de.berlios.imilarity.image.Image#getHeight()
	 */
	public int getHeight() {
		return imageData.getHeight();
	}

}
