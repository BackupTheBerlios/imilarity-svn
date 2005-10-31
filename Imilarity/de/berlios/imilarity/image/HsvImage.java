package de.berlios.imilarity.image;

public class HsvImage extends ImageBase {
	private Image image;

	public HsvImage(Image image) {
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
		
		double s = 0.0;
		if (max > 0)
			s = (max - min) / max;
		
		return new Color(h / 360, s, max);
	}

	public Image getScaledInstance(int w, int h) {
		return new HueImage(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
