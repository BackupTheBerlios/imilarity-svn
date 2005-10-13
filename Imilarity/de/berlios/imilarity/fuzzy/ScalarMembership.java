package de.berlios.imilarity.fuzzy;

public class ScalarMembership implements Membership {

	private double value;
	
	public ScalarMembership(double membership) {
		//if (membership < 0 || membership > 1)
		//	throw new IllegalArgumentException("0 <= membership <= 1 must be satistied");
		this.value = membership;
	}
	
	protected double getValue() {
		return value;
	}
	
	protected void setValue(double value) {
		this.value = value;
	}
	
	
	
	public double abs() {
		return Math.abs(value);
	}

	public Membership plus(Membership m) {
		if (!(m instanceof ScalarMembership))
			throw new IllegalArgumentException("argument must be an instance of ScalarMembership");
		return new ScalarMembership(value + ((ScalarMembership)m).value);
	}

	public Membership minus(Membership m) {
		if (!(m instanceof ScalarMembership))
			throw new IllegalArgumentException("argument must be an instance of ScalarMembership");
		return new ScalarMembership(value - ((ScalarMembership)m).value);
	}

	public Membership and(Membership m) {
		if (!(m instanceof ScalarMembership))
			throw new IllegalArgumentException("argument must be an instance of ScalarMembership");
		return new ScalarMembership(Math.max(value, ((ScalarMembership)m).value));
	}

	public Membership or(Membership m) {
		if (!(m instanceof ScalarMembership))
			throw new IllegalArgumentException("argument must be an instance of ScalarMembership");
		return new ScalarMembership(Math.min(value, ((ScalarMembership)m).value));
	}

	public Membership complement() {
		return new ScalarMembership(1 - value);
	}
	
	public boolean equals(Membership m) {
		if (!(m instanceof ScalarMembership))
			throw new IllegalArgumentException("argument must be an instance of ScalarMembership");
		return value == ((ScalarMembership)m).value;
	}
}
