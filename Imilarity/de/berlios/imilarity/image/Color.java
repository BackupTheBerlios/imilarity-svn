package de.berlios.imilarity.image;

import de.berlios.imilarity.fuzzy.Membership;
import de.berlios.imilarity.fuzzy.VectorMembership;

public class Color implements Membership {

	private double[] components;
	
	
	public Color(double[] components) {
		if (components == null)
			throw new NullPointerException("values == null");
		this.components = components;
	}
	
	public Color(double component) {
		this(new double[] { component });
	}
	
	
	public double[] getComponents() {
		double[] componentsCopy = new double[components.length];
		for (int i = 0; i < components.length; i++)
			componentsCopy[i] = components[i];
		return componentsCopy;
	}
	
	
	// Implementatie van Membership
	
	public double abs() {
		double sum = 0;
		for (int i = 0; i < components.length; i++)
			sum += components[i] * components[i];
		return Math.sqrt(sum) / Math.sqrt(components.length);
	}

	public Membership plus(Membership m) {
		if (!(m instanceof VectorMembership) || 
				((Color)m).components.length != components.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + components.length);
		double newComponents[] = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = components[i] + ((Color)m).components[i];
		return new VectorMembership(newComponents);
	}

	public Membership minus(Membership m) {
		if (!(m instanceof VectorMembership) || 
				((Color)m).components.length != components.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + components.length);
		double newComponents[] = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = components[i] - ((Color)m).components[i];
		return new VectorMembership(newComponents);
	}

	public Membership and(Membership m) {
		// TODO Auto-generated method stud
		throw new UnsupportedOperationException("TODO");
	}

	public Membership or(Membership m) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO");
	}

	public Membership complement() {
		double newComponents[] = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = 1 - components[i];
		return new VectorMembership(newComponents);
	}

	public boolean equals(Membership m) {
		if (!(m instanceof VectorMembership) || 
				((Color)m).components.length != components.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + components.length);
		for (int i = 0; i < components.length; i++)
			if (components[i] != ((Color)m).components[i])
				return false;
		return true;
	}
	
}
