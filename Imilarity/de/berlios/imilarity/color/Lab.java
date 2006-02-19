package de.berlios.imilarity.color;

public class Lab extends Xyz {

	private static final double X_REF = 95.05;
	//private static final double Y_REF = 100.0;
	private static final double Z_REF = 108.9; 
	
	public Color fromRgb(int[] rgb) {
		double xyz[] = super.fromRgb(rgb).getComponents();
		double l = 116.0 * f(xyz[1]) - 16;
		double a = 500.0 * (f(100*xyz[0]/X_REF) - f(xyz[1]));
		double b = 200.0 * (f(xyz[1]) - f(100*xyz[2]/Z_REF));
		if (l > 100.0) l = 100.0;
		else if (l < 0.0) l = 0.0;
		if (a > 120.0) a = 120.0;
		else if (a < -120.0) a = -120.0;
		if (b > 120.0) b = 120.0;
		else if (b < -120.0) b = -120.0;
		return new Color(l/100.0, (120+a)/240.0, (120+b)/240.0);
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
		double fy = (comps[0]*100 + 16)/116.0;
		double fx = fy + (comps[1]*240.0 - 120)/500.0;
		double fz = fy - (comps[2]*240.0 - 120)/200.0;
		if (fy > d) y = fy*fy*fy;
		else 		y = (fy - 16.0/116.0)*3*d*d;
		if (fx > d) x = X_REF*fx*fx*fx/100.0;
		else		x = (fx - 16.0/116.0)*3*d*d*X_REF/100.0;
		if (fz > d) z = Z_REF*fz*fz*fz/100.0;
		else		z = (fz - 16.0/116.0)*3*d*d*Z_REF/100.0;
		return super.toRgb(new Color(x,y,z));
	}
}
