package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Maximum;
import de.berlios.imilarity.fuzzy.Membership;

public class M14a extends SimplifiedFuzzyMeasure {

	public M14a() {
		super(new Maximum());
	}
	
	public double m(Membership x, Membership y) {
		return x.and(y).abs();
	}

	public String getDescription() {
		return "M14a";
	}

}
