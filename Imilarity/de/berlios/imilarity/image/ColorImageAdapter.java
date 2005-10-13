/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.image;


/**
 * @author Klaas Bosteels
 */
public class ColorImageAdapter extends ColorImageBase {

	private ImageData imageData;
	
	public ColorImageAdapter(ImageData id) {
		if (id == null)
			throw new NullPointerException("id == null");
		imageData = id;
	}
	
	
	/**
	 * @see de.berlios.imilarity.image.ColorImage#getColor(int, int)
	 */
	public Color getColor(int x, int y) {
		int[] rgb = imageData.getRgb(x, y);
		double r = rgb[0] * 1.0 / 255;
		double g = rgb[1] * 1.0 / 255;
		double b = rgb[2] * 1.0 / 255;
		return new Color(new double[] { r, g, b });
	}

	/**
	 * @see de.berlios.imilarity.image.ColorImage#getScaledInstance(int, int)
	 */
	public ColorImage getScaledInstance(int w, int h) {
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
