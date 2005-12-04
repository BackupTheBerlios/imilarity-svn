package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;

public class I1i2i3Image extends ImageBase {

	private Image image;

	public I1i2i3Image(Image image) {
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
		
		//r = Math.pow(r, 1.0/3);
		//g = Math.pow(g, 1.0/3);
		//b = Math.pow(b, 1.0/3);
		
		//return new Color(new double[] { (1 - (r-g))/2, (1 - (((r+g)/2) - b))/2,
		//			(r+g+b)/3});
		return new Color((r+g+b)/3, (r + (1-b))/2, (r + 2*(1-g) + b)/4);
	}

	public Image getScaledInstance(int w, int h) {
		return new I1i2i3Image(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}
}
