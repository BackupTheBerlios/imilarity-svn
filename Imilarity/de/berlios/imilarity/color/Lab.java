package de.berlios.imilarity.color;

public class Lab extends Xyz {

	private static final double X_REF = 95.05;
	//private static final double Y_REF = 100.0;
	private static final double Z_REF = 108.9; 
	
	private static final double A_RANGE = 120.0, B_RANGE = 120.0;
	
	public Color fromRgb(int[] rgb) {
		double xyz[] = super.fromRgb(rgb).getComponents();
		double l = 116.0 * f(xyz[1]) - 16;
		double a = 500.0 * (f(100*xyz[0]/X_REF) - f(xyz[1]));
		double b = 200.0 * (f(xyz[1]) - f(100*xyz[2]/Z_REF));
		if (l > 100.0) l = 100.0;
		else if (l < 0.0) l = 0.0;
		if (a > A_RANGE) a = A_RANGE;
		else if (a < -A_RANGE) a = -A_RANGE;
		if (b > B_RANGE) b = B_RANGE;
		else if (b < -B_RANGE) b = -B_RANGE;
		return new Color(l/100.0, (A_RANGE+a)/(2*A_RANGE), (B_RANGE+b)/(2*B_RANGE));
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


	
	// TESTPROGRAMMA
	
	public static void main(String[] args) {
		int[] comps = (new Lab()).toRgb(new Color(1, 0.0625, 1));
		for (int i = 0; i < comps.length; i++)
			System.out.print(""+i+": "+comps[i]+" ");
		System.out.println();
		comps = (new Lab()).toRgb(new Color(1, 0.1875, 1));
		for (int i = 0; i < comps.length; i++)
			System.out.print(""+i+": "+comps[i]+" ");
		System.out.println();
	}
}
