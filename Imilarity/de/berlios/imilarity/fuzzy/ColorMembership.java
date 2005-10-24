package de.berlios.imilarity.fuzzy;

import de.berlios.imilarity.image.Color;

public class ColorMembership extends SimpleMembership {
	
	public ColorMembership(Color color) {
		super(color.getComponents());
	}
	
	public ColorMembership(double[] components) {
		super(components);
	}
	
	public ColorMembership(Membership sms) {
		super(sms.getComponents());
	}
	
	
	public Membership plus(Membership m) {
		return new ColorMembership(super.plus(m));
	}
	
	public Membership minus(Membership m) {
		return new ColorMembership(super.minus(m));
	}

	public Membership and(Membership m) {
		return new ColorMembership(super.and(m));
	}

	public Membership or(Membership m) {
		return new ColorMembership(super.or(m));
	}
	
	public Membership complement() {
		return new ColorMembership(super.complement());
	}

	
	public int compareTo(Object obj) { // ordening van valerie gebruiken
		if (!(obj instanceof Membership))
			throw new IllegalArgumentException("must be an instance of Membership");
		Membership m = (Membership) obj;
		if (getComponents().length != m.getComponents().length)
			throw new IllegalArgumentException("must have same component count (" 
					+ getComponents().length + " != " + m.getComponents().length + ")");
		Membership black = new SimpleMembership(0,getComponents().length);
		Membership white = new SimpleMembership(1,getComponents().length);
		
		double dw1 = distanceTo(white), dw2 = m.distanceTo(white);
		double db1 = distanceTo(black), db2 = m.distanceTo(black);
		
		if (dw1 < dw2) return 1; 		// max(this,m) = this 
		else if (dw1 > dw2) return -1; 	// max(this,m) = m
		else if (db1 > db2) return 1; 	// max(this,m) = this
		else if (db1 < db2) return -1; 	// max(this,m) = m
		else return super.compareTo(obj);
	}
}
