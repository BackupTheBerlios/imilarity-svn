package de.berlios.imilarity.fuzzy;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.quantizers.Quantizer;


public class FuzzyColor extends FuzzySetBase {

	private Color color;
	private Quantizer quantizer; 
	
	public FuzzyColor(Color color, Quantizer quantizer) {
		if (color == null)
			throw new NullPointerException("color == null");
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.color = color;
		this.quantizer = quantizer;
	}
	
	public int getElementsCount() {
		return quantizer.getBinsCount();
	}

	
	public Membership getMembership(int element) {
		Color otherColor = quantizer.getBinColor(element);
		double d = distance(color, otherColor);
		if (d <= 2.3) {
			return new SimpleMembership(1);
		} else {
			double v = (d/2.3) - 1;
			//System.out.println("v = " +v);
			//System.out.println("m = " + Math.exp(-(v*v/100000.0)));
			double m = Math.exp(-(v*v/50.0));
			if (m < 0.01) m = 0;
			return new SimpleMembership(m);
		}
	}
	
	private static double distance(Color c1, Color c2) {
		double[] comps1 = c1.getComponents();
		double[] comps2 = c2.getComponents();
		int d1 = (int)(comps1[0]*100) - (int)(comps2[0]*100);
		int d2 = (int)(comps1[1]*240) - (int)(comps2[1]*240);
		int d3 = (int)(comps1[2]*240) - (int)(comps2[2]*240);
		return Math.sqrt(d1*d1+d2*d2+d3*d3);
	}

}
