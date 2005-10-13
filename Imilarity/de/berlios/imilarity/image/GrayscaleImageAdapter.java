/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.image;

public class GrayscaleImageAdapter extends ColorImageBase {

	private ColorImage image;

	public GrayscaleImageAdapter(ColorImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}

	public Color getColor(int x, int y) {
		double[] colors = image.getColor(x, y).getComponents();
		return new Color(0.3 * colors[0] + 0.59 * colors[1] + 0.11 * colors[2]);
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public ColorImage getScaledInstance(int w, int h) {
		return new GrayscaleImageAdapter(image.getScaledInstance(w,h));
	}
	
}
