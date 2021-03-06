package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;


public class HmmdImage extends ImageBase {

	private Image image;

	public HmmdImage(Image image) {
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
		double max = Math.max(r,Math.max(g,b));
		double min = Math.min(r,Math.min(g,b));
		double h = 0.0;
		if (max == min) 	h = 0;
		else if (max == r)	h = 60 * (g - b) / (max - min);
		else if (max == g)	h = (60 * (b - g) / (max - min)) + 120;
		else 				h = (60 * (r - g) / (max - min)) + 240;
		while (h < 0) 	h = 360 - h;
		while (h > 360)	h = h - 360; 
		return new Color(h / 360, (min+max)/2, max-min);
	}

	public Image getScaledInstance(int w, int h) {
		return new HmmdImage(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
