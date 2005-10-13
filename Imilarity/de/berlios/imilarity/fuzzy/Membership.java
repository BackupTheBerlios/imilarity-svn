package de.berlios.imilarity.fuzzy;

public interface Membership {

	public double abs();
	
	public Membership plus(Membership m);
	public Membership minus(Membership m);
	
	public Membership and(Membership m);
	public Membership or(Membership m);

	public Membership complement();
	
	public boolean equals(Membership m);
}
