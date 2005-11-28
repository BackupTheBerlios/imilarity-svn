package de.berlios.imilarity.image;

public class LabImage extends XyzImage {

	public LabImage(Image image) {
		super(image);
	}
	
	public Color getColor(int x, int y) {
		double xyz[] = super.getColor(x,y).getComponents();
		double l = 116 * f(xyz[1]) - 16;
		double a = 500 * (f(xyz[0]) - f(xyz[1]));
		double b = 200 * (f(xyz[0]) - f(xyz[2]));
		return new Color(l/100, (500+a)/1000, (200+b)/400);
	}
	
	private static final double f(double t) {
		if (t > 0.008856)
			return Math.pow(t, 1.0/3.0);
		else
			return 7.787 * t + (16.0/116.0);
	}
}
