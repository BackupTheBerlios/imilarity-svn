package de.berlios.imilarity.measures;

import java.util.Iterator;

import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.Membership;
import de.berlios.imilarity.util.CombinedIterator;


public class M3 extends FuzzyMeasureBase {
	
	public double getSimilarity() {
		double sum = 0.0;
		FuzzySet a = getQuery();
		FuzzySet b = getTarget();
		Iterator it = new CombinedIterator(a.iterator(), b.iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			Membership m1 = a.getMembership(i);
			Membership m2 = b.getMembership(i);
			sum += m1.minus(m2).abs();
		}
		double d = (a.abs() + b.abs());
		if (d == 0)
			return 0;
		else
			return 1 - (sum / d);
	}


	public String getDescription() {
		return "M3";
	}

}
