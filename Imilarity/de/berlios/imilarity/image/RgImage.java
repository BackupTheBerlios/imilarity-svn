package de.berlios.imilarity.image;

public class RgImage extends ImageBase {

	private Image image;

	public RgImage(Image image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
	}
	
	public int getColorComponentsCount() {
		return 2;
	}
	
	public Color getColor(int x, int y) {
		double[] rgb = image.getColor(x,y).getComponents();
		double r = rgb[0], g = rgb[1], b = rgb[2];
		if (r == 0 && g == 0 && b == 0)
			return new Color(new double[] { 0.0, 0.0 });
		else
			return new Color(new double[] { r/(r+g+b), g/(r+g+b) });
	}

	public Image getScaledInstance(int w, int h) {
		return new RgImage(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
