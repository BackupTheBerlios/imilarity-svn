/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.image;

public class ScalableGrayscaleImageAdapter implements ScalableGrayscaleImage {

	private ScalableColorImage image;

	public ScalableGrayscaleImageAdapter(ScalableColorImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}

	public int getGrayscaleValue(int pixelNr) {
		int[] colors = image.getColorValues(pixelNr);
		return (int) (0.3 * colors[0] + 0.59 * colors[1] + 0.11 * colors[2]);
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public ScalableGrayscaleImage getScaledInstance(int w, int h) {
		return new ScalableGrayscaleImageAdapter(image.getScaledInstance(w,h));
	}
	
}
