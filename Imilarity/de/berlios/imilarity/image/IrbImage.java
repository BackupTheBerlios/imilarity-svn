package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;

public class IrbImage extends ImageBase {

	private Image image;

	public IrbImage(Image image) {
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
		return new Color((r+g+b)/3, r/(r+g+b), b/(r+g+b));
	}

	public Image getScaledInstance(int w, int h) {
		return new IrbImage(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}
	
}
