package de.berlios.imilarity.color;

public class I1i2i3 implements ColorSpace {

	public Color fromRgb(int[] rgb) {
		double r = rgb[0]/255.0, g = rgb[1]/255.0, b = rgb[2]/255.0;
		return new Color((r+g+b)/3, (r + (1-b))/2, (r + 2*(1-g) + b)/4);
	}

	public int[] toRgb(Color color) {
		double[] comps = color.getComponents();
//		double r = comps[0] + comps[1] - (2.0/3.0)*comps[2] - (5.0/6.0);
//		double g = comps[0] - (4.0/3.0)*comps[2] + (2.0/3.0);
//		double b = comps[0] - comps[1] + (2.0/3.0)*comps[2] + (1.0/6.0);
		double r = comps[0] + comps[1] + (2.0/3.0)*comps[2] - (5.0/6.0);
		double g = comps[0] - (4.0/3.0)*comps[2] + (2.0/3.0);
		double b = comps[0] - comps[1] + (2.0/3.0)*comps[2] + (1.0/6.0);
		//System.out.println("r = " + r);
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
