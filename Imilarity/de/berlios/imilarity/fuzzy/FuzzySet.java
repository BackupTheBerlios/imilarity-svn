package de.berlios.imilarity.fuzzy;

import java.util.Iterator;

public interface FuzzySet {

	public int getElementsCount();
	
	/**
	 * Geeft een iterator terug waarmee men de indices
	 * van de elementen kan overlopen. De indices die
	 * overeenkomen met elementen die lidmaatschapsgraad
	 * 0 hebben, kunnen eventueel worden overgeslagen
	 * in die iteratie. Er wordt wel gegarandeerd dat de
	 * volgende indices telkens groter zijn dan de vorige.
	 * @return de gevraagde iterator
	 */
	public Iterator iterator(); 
	
	public Membership getMembership(int element);

	
	public FuzzySet intersection(FuzzySet set);
	
	public FuzzySet union(FuzzySet set);
	
	public FuzzySet complement();
	
	public FuzzySet minus(FuzzySet set);
	
	public double abs();
	
	
	// Speciale operaties (toegevoegd om beperken rekentijd
	// mogelijk te maken):
	
	/**
	 * (this \cap set) \cap (this^c \cap set^c)
	 */
	public FuzzySet iii(FuzzySet set);
	
	/**
	 * (this \cup set) \cap (this^c \cup set^c)
	 */
	public FuzzySet uiu(FuzzySet set);
	
}
