package de.berlios.imilarity.fuzzy;


public class Membership implements Comparable {

	private double[] components;

	public Membership(double[] components) {
		if (components == null)
			throw new NullPointerException("values == null");
		this.components = components;
	}
	
	public Membership(double component) {
		this(new double[] { component });
	}
	
	public double[] getComponents() {
		double[] componentsCopy = new double[components.length];
		for (int i = 0; i < components.length; i++)
			componentsCopy[i] = components[i];
		return componentsCopy;
	}
	
	
	public double abs() {
		double sum = 0;
		for (int i = 0; i < components.length; i++)
			sum += components[i] * components[i];
		return Math.sqrt(sum) / Math.sqrt(3);
	}
	
	public Membership plus(Membership m) {
		if (!(m instanceof Membership) ||
				((Membership)m).components.length != components.length)
			throw new IllegalArgumentException
			("argument must be a VectorComponent of size " + components.length);
		double newComponents[] = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = components[i] + ((Membership)m).components[i];
		return new Membership(newComponents);
	}
	
	public Membership minus(Membership m) {
		if (!(m instanceof Membership) || 
				((Membership)m).components.length != components.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + components.length);
		double newComponents[] = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = components[i] - ((Membership)m).components[i];
		return new Membership(newComponents);
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
		return new Membership(newComponents);
	}

	public boolean equals(Membership m) {
		if (!(m instanceof Membership) || 
				((Membership)m).components.length != components.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + components.length);
		for (int i = 0; i < components.length; i++)
			if (components[i] != ((Membership)m).components[i])
				return false;
		return true;
	}

	
	public int compareTo(Object obj) {
		if (!(obj instanceof Membership))
			throw new IllegalArgumentException("obj not an instance of Membership");
		int comp = 0;
		for (int i = 0; i < components.length; i++) {
			comp = (new Double(components[i])).compareTo
				(new Double(((Membership)obj).components[i]));
			if (comp != 0)
				return comp;
		}
		return comp;
	}
}
