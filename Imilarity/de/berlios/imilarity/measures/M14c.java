package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Maximum;
import de.berlios.imilarity.fuzzy.Membership;
import de.berlios.imilarity.fuzzy.SimpleMembership;

public class M14c extends AggregatedFuzzyMeasure {

	public M14c() {
		super(new Maximum());
	}
	
	public double m(Membership x, Membership y) {
		int count = x.getComponents().length;
		return x.plus(y).minus
			(new SimpleMembership(1,count)).and(new SimpleMembership(0,count)).abs();
	}

	public String getDescription() {
		return "M14c";
	}

}
