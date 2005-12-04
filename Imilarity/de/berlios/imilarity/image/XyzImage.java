package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;

public class XyzImage extends ImageBase {

	private Image image;

	public XyzImage(Image image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
	}
	
	public int getColorComponentsCount() {
		return 3;
	}
	
	public Color getColor(int x, int y) {
		double[] rgb = image.getColor(x,y).getComponents();
		double r = rgb[0], g = rgb[1], b = rgb[2];
		return new Color(new double[] { 
				0.431*r+0.342*g+0.178*b,
				0.222*r+0.707*g+0.071*b,
				0.020*r+0.130*g+0.939*b
			});
	}

	public Image getScaledInstance(int w, int h) {
		return new XyzImage(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
