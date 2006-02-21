package de.berlios.imilarity.measures;

import java.util.Iterator;

import de.berlios.imilarity.util.CombinedIterator;

public class M1c extends FuzzyMeasureBase {

	public double getSimilarity() {
		int count = getQuery().getElementsCount();
		double sum = 0.0;
		Iterator it = new CombinedIterator
			(getQuery().iterator(), getTarget().iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			double v = getQuery().getMembership(i).minus(getTarget().getMembership(i)).abs();
			sum += v*v*v*v;
		}
		return 1 - Math.pow(sum / count, 1.0/4.0);
	}

	public String getDescription() {
		return "M1c";
	}

}
