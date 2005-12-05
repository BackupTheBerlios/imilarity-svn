package de.berlios.imilarity.color;


public class Hsv implements ColorSpace {

	public Color fromRgb(int[] rgb) {
		double r = rgb[0]/255.0, g = rgb[1]/255.0, b = rgb[2]/255.0;
		double max = Math.max(r,Math.max(g,b));
		double min = Math.min(r,Math.min(g,b));
		
		double h = 0.0;
		if (max == min) 	h = 0;
		else if (max == r)	h = 60 * (g - b) / (max - min);
		else if (max == g)	h = (60 * (b - r) / (max - min)) + 120;
		else 				h = (60 * (r - g) / (max - min)) + 240;
		
		h %= 360;
		if (h < 0) h += 360;
					
		double s = 0.0;
		if (max > 0)
			s = (max - min) / max;
		
		return new Color(h/360, s, max);
	}

	public int[] toRgb(Color color) {
		double[] comps = color.getComponents();
		double h = comps[0]*6;
		double s = comps[1];
		double v = comps[2];
		int i = (int) h;
		double f = h - i;
		if(!((i & 1) == 1)) f = 1 - f; // if i is even
		double m = v * (1 - s);
		double n = v * (1 - s * f);
		switch (i) {
			case 0: return new int[] {(int)(v*255), (int)(n*255), (int)(m*255)};
			case 1: return new int[] {(int)(n*255), (int)(v*255), (int)(m*255)};
			case 2: return new int[] {(int)(m*255), (int)(v*255), (int)(n*255)};
			case 3: return new int[] {(int)(m*255), (int)(n*255), (int)(v*255)};
			case 4: return new int[] {(int)(n*255), (int)(m*255), (int)(v*255)};
			case 5: return new int[] {(int)(v*255), (int)(m*255), (int)(n*255)};
		}
		return new int[] {0,0,0};
	}

	public int getComponentsCount() {
		return 3;
	}

}
