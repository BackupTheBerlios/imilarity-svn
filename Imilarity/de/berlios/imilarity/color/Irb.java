package de.berlios.imilarity.color;

public class Irb implements ColorSpace {

	public Color fromRgb(int[] rgb) {
		double r = rgb[0]/255.0, g = rgb[1]/255.0, b = rgb[2]/255.0;
		return new Color((r+g+b)/3, r/(r+g+b), b/(r+g+b));
	}

	public int[] toRgb(Color color) {
		double[] comps = color.getComponents();
		double r = 3*comps[1]*comps[0];
		double g = 3*comps[0] - 3*comps[1]*comps[0] - 3*comps[2]*comps[0];
		double b = 3*comps[2]*comps[0];
		if (r < 0) r = 0;
		else if (r > 1) r = 1;
		if (g < 0) g = 0;
		else if (g > 1) g = 1;
		if (b < 0) b = 0;
		else if (b > 1) b = 1;
		return new int[] {(int)(r*255),(int)(g*255),(int)(b*255)};
	}

	public int getComponentsCount() {
		return 3;
	}

}
