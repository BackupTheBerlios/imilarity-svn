package de.berlios.imilarity.fuzzy;

public class SimpleMembership implements Membership {
	private double[] components;

	public SimpleMembership(double[] components) {
		if (components == null)
			throw new NullPointerException("values == null");
		this.components = components;
	}
	
	public SimpleMembership(double component) {
		this(new double[] { component });
	}
	
	public SimpleMembership(double component, int componentsCount) {
		double[] components = new double[componentsCount];
		for (int i = 0; i < components.length; i++)
			components[i] = component;
		this.components = components;
	}
	
	public SimpleMembership(Membership ms) {
		this(ms.getComponents());
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
		if (m.getComponents().length != components.length)
			throw new IllegalArgumentException
			("argument must be a Membership of size " + components.length);
		double newComponents[] = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = components[i] + m.getComponents()[i];
		return new SimpleMembership(newComponents);
	}
	
	public Membership minus(Membership m) {
		if (m.getComponents().length != components.length)
			throw new IllegalArgumentException
				("argument must be a Membership of size " + components.length);
		double newComponents[] = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = components[i] - m.getComponents()[i];
		return new SimpleMembership(newComponents);
	}

	public Membership and(Membership m) { // minimum
		return new SimpleMembership((compareTo(m) <= 0) ? this : m);
	}

	public Membership or(Membership m) { // maximum
		return new SimpleMembership((compareTo(m) >= 0) ? this : m);
	}

	public Membership complement() {
		double[] newComponents = new double[components.length];
		for (int i = 0; i < components.length; i++)
			newComponents[i] = 1 - components[i];
		if (newComponents.length != 3)
			System.out.println("newcomps.length: " + newComponents.length);
		return new SimpleMembership(newComponents);
	}

	public boolean equals(Membership m) {
		if (m.getComponents().length != components.length)
			throw new IllegalArgumentException
				("argument must be a Membership of size " + components.length);
		for (int i = 0; i < components.length; i++)
			if (components[i] != m.getComponents()[i])
				return false;
		return true;
	}

	public double distanceTo(Membership m) {
		double[] comps1 = getComponents();
		double[] comps2 = m.getComponents();
		double sum = 0.0;
		for (int i = 0; i < comps1.length; i++) {
			double difference = comps1[i] - comps2[i];
			sum += difference * difference;
		}
		return Math.sqrt(sum) / Math.sqrt(comps1.length);
	}
	
	
	public int compareTo(Object obj) {
		if (!(obj instanceof Membership))
			throw new IllegalArgumentException("obj not an instance of Membership");
		int comp = 0;
		for (int i = 0; i < components.length; i++) {
			comp = (new Double(components[i])).compareTo
				(new Double(((Membership)obj).getComponents()[i]));
			if (comp != 0)
				return comp;
		}
		return comp;
	}
}
