package de.berlios.imilarity.fuzzy;

public class VectorMembership implements Membership {

	private double[] values;
	
	public VectorMembership(double[] values) {
		if (values == null)
			throw new NullPointerException("values == null");
		this.values = values;
	}
	
	protected double[] getValues() {
		return values;
	}
	
	protected void setValues(double[] values) {
		if (values == null)
			throw new NullPointerException("values == null");
		this.values = values;
	}
	
	
	
	public double abs() {
		double sum = 0;
		for (int i = 0; i < values.length; i++)
			sum += values[i] * values[i];
		return Math.sqrt(sum) / Math.sqrt(values.length);
	}

	public Membership plus(Membership m) {
		if (!(m instanceof VectorMembership) || 
				((VectorMembership)m).values.length != values.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + values.length);
		double newComponents[] = new double[values.length];
		for (int i = 0; i < values.length; i++)
			newComponents[i] = values[i] + ((VectorMembership)m).values[i];
		return new VectorMembership(newComponents);
	}

	public Membership minus(Membership m) {
		if (!(m instanceof VectorMembership) || 
				((VectorMembership)m).values.length != values.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + values.length);
		double newComponents[] = new double[values.length];
		for (int i = 0; i < values.length; i++)
			newComponents[i] = values[i] - ((VectorMembership)m).values[i];
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
		double newComponents[] = new double[values.length];
		for (int i = 0; i < values.length; i++)
			newComponents[i] = 1 - values[i];
		return new VectorMembership(newComponents);
	}

	public boolean equals(Membership m) {
		if (!(m instanceof VectorMembership) || 
				((VectorMembership)m).values.length != values.length)
			throw new IllegalArgumentException
				("argument must be a VectorComponent of size " + values.length);
		for (int i = 0; i < values.length; i++)
			if (values[i] != ((VectorMembership)m).values[i])
				return false;
		return true;
	}

}
