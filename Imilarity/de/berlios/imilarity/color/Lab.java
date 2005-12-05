package de.berlios.imilarity.color;

public class Lab extends Xyz {

	public Color fromRgb(int[] rgb) {
		double xyz[] = super.fromRgb(rgb).getComponents();
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
	
	
	private static final double d = 6.0/29.0;
	
	public int[] toRgb(Color color) {
		double[] comps = color.getComponents();
		double x, y, z;
		double fy = (comps[0]*100 + 16)/116;
		double fx = fy + (comps[1]*1000 - 500)/500;
		double fz = fy - (comps[2]*400 - 200)/200;
		if (fy > d) y = fy*fy*fy;
		else 		y = (fy - 16.0/116.0)*3*d*d;
		if (fx > d) x = fx*fx*fx;
		else		x = (fx - 16.0/116.0)*3*d*d;
		if (fz > d) z = fz*fz*fz;
		else		z = (fz - 16.0/116.0)*3*d*d;
		return super.toRgb(new Color(x,y,z));
	}
}
