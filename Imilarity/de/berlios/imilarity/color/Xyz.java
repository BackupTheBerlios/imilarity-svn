package de.berlios.imilarity.color;

public class Xyz implements ColorSpace {

	public Color fromRgb(int[] rgb) {
		double r = rgb[0]/255.0;
		double g = rgb[1]/255.0;
		double b = rgb[2]/255.0;
		/*
		return new Color(new double[] { 
				0.431*r+0.342*g+0.178*b,
				0.222*r+0.707*g+0.071*b,
				0.020*r+0.130*g+0.939*b
			});
			*/
		return new Color(new double[] { 
				0.4124*r + 0.3576*g + 0.1805*b,
				0.2126*r + 0.7152*g + 0.0722*b,
				0.0193*r + 0.1192*g + 0.9505*b
			});
	}

	public int[] toRgb(Color color) {
		double[] comps = color.getComponents();
		/*
		double r = 3.059589890 * comps[0] - 0.4746773243 * comps[2] - 1.392746380 * comps[1];
		double g = 0.04166582988 * comps[2] + 1.874840829 * comps[1] - 0.9676287255 * comps[0];
		double b = -0.2298981685 * comps[1] + 0.06879652450 * comps[0] + 1.069304567 * comps[2];
		*/
		double r =  3.240625477   * comps[0] - 0.4986285987  * comps[2] - 1.537207972  * comps[1];
		double g =  0.04151752384 * comps[2] + 1.875756061   * comps[1] - 0.9689307147 * comps[0];
		double b = -0.2040210506  * comps[1] + 0.05571012045 * comps[0] + 1.056995942  * comps[2];
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
