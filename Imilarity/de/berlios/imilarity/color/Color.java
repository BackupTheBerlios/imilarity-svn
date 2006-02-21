package de.berlios.imilarity.color;


public class Color implements Comparable {

	private final double[] components;
	
	public Color(double[] components) {
		if (components == null)
			throw new NullPointerException("components == null");
		this.components = components;
	}
	
	public Color(double c1, double c2, double c3) {
		this(new double[] { c1, c2, c3 });
	}
	
	public Color(double component) {
		this(new double[] { component });
	}
	
	public double[] getComponents() {
		return components;
	}

	
	public int compareTo(Object obj) {
		Color c = (Color) obj;
		for (int i = 0; i < components.length; i++)
			if (components[i] < c.components[i])
				return -1;
			else if (components[i] > c.components[i])
				return 1;
		return 0;
	}
}
