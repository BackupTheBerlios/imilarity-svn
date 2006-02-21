/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.Iterator;

import de.berlios.imilarity.util.CombinedIterator;


public class M2 extends FuzzyMeasureBase {
	
	public double getSimilarity() {
		double max = 0.0;
		Iterator it = new CombinedIterator
			(getQuery().iterator(), getTarget().iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			double v = getQuery().getMembership(i).minus(getTarget().getMembership(i)).abs();
			if (v > max) max = v; 
		}
		return 1 - max;
	}
	
	public String getDescription() {
		return "M2";
	}

}
