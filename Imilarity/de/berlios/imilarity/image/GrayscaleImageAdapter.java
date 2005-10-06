/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.image;

public class GrayscaleImageAdapter extends GrayscaleImageBase {

	private ColorImage image;

	public GrayscaleImageAdapter(ColorImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}

	public int getGrayscaleValue(int x, int y) {
		int[] colors = image.getColorValues(x, y);
		return (int) (0.3 * colors[0] + 0.59 * colors[1] + 0.11 * colors[2]);
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public GrayscaleImage getScaledInstance(int w, int h) {
		return new GrayscaleImageAdapter(image.getScaledInstance(w,h));
	}
	
}
