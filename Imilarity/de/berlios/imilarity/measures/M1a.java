/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.Iterator;
import de.berlios.imilarity.util.CombinedIterator;


public class M1a extends FuzzyMeasureBase {

	public double getSimilarity() {
		int count = getQuery().getElementsCount();
		double sum = 0.0;
		Iterator it = new CombinedIterator
			(getQuery().iterator(), getTarget().iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			sum += getQuery().getMembership(i).minus(getTarget().getMembership(i)).abs();
		}
		return 1 - (sum / count);
	}
	
	public String getDescription() {
		return "M1a";
	}
}
