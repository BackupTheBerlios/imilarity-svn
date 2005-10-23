package de.berlios.imilarity.image;

import de.berlios.imilarity.fuzzy.Membership;


public class Color extends Membership {

	public Color(double[] components) {
		super(components);
	}
	
	public Color(double c1, double c2, double c3) {
		super(new double[] { c1, c2, c3 });
	}
	
	public Color(double component) {
		super(component);
	}
	
}
