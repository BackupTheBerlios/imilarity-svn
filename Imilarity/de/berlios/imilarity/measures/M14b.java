package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Maximum;
import de.berlios.imilarity.fuzzy.Membership;

public class M14b extends AggregatedFuzzyMeasure {

	public M14b() {
		super(new Maximum());
	}
	
	public double m(Membership x, Membership y) {
		return x.abs() * y.abs();
	}

	public String getDescription() {
		return "M14b";
	}

}
