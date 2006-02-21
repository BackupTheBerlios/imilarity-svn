package de.berlios.imilarity.fuzzy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.smoothers.DefaultSmoother;
import de.berlios.imilarity.smoothers.Smoother;
import de.berlios.imilarity.util.CombinedIterator;

public class PseudoFuzzyHistogram extends FuzzySetBase {

	private Map histogram;
	private SortedSet elements;
	private int elementsCount = 0;
	private double max = 0.0;
	private Smoother smoother;
	
	
	public PseudoFuzzyHistogram(Image image, int[] binsCounts) {
		if (image == null)
			throw new NullPointerException("image == null");
		if (binsCounts == null)
			throw new NullPointerException("binsCounts == null");
		if (image.getColorComponentsCount() != binsCounts.length)
			throw new IllegalArgumentException("binsCounts must have " 
					+ image.getColorComponentsCount() + " components");
		this.smoother = new DefaultSmoother();
		
		histogram = new HashMap();
		elementsCount = binsCounts[0];
		for (int i = 1; i < image.getColorComponentsCount(); i++)
			elementsCount *= binsCounts[i];
		
		elements = new TreeSet();
		
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			int intIndex = 
				Math.min((int)(image.getColor(i).getComponents()[0]*binsCounts[i]),binsCounts[i]-1);
			Integer index = new Integer(intIndex);
			Integer prev = (Integer)histogram.get(index);
			if (prev == null) {
				elements.add(index);
				prev = new Integer(0);
			}
			
			int newValue = prev.intValue()+1;
			histogram.put(index, new Integer(newValue));
			if (newValue > max) max = newValue;
		}
	}
	
	public PseudoFuzzyHistogram(Map histogram, int elementsCount, Smoother smoother) {
		if (histogram == null)
			throw new NullPointerException("histogram == null");
		this.histogram = histogram;
		if (smoother == null)
			throw new IllegalArgumentException("smoother == null");
		this.smoother = smoother;
		this.elementsCount = elementsCount;
		
		elements = new TreeSet();
		
		max = 0.0;
		Iterator it = histogram.keySet().iterator();
		//for (int j = 0; j < elementsCount; j++) {
		while (it.hasNext()) {
			Integer jj = (Integer)it.next();
			elements.add(jj);
			int j = jj.intValue();
			int n = smoother.getRange();
			double avg = 0.0;
			for (int i = -n; i <= n; i++) {
				int index = smoother.getIndex(j, i);
				if (index >= 0) {
					Integer value = (Integer) histogram.get(new Integer(j+i));
					if (value == null) 
						value = new Integer(0);
					avg += smoother.getIncrement(j, i, value.intValue());
				}
			}
			double v = avg/(2*n+1);
			if (v > max) max = v;
		}
		//System.out.println("narrowElementsCount: " + elements.size());
		//Iterator tmp = elements.iterator();
		//while (tmp.hasNext())
		//	System.out.print(tmp.next() + " ");
		//System.out.println();
	}
	
	public PseudoFuzzyHistogram(Map histogram, int elementsCount) {
		this(histogram, elementsCount, new DefaultSmoother());
	}
	
	
	
	public int getElementsCount() {
		return elementsCount;
	}
	
	public Iterator iterator() {
		if (elements == null)
			return null;
		return elements.iterator();
	}

	public Membership getMembership(int element) {
		int n = smoother.getRange();
		double avg = 0.0;
		for (int i = -n; i <= n; i++) {
			int index = smoother.getIndex(element, i);
			if (index >= 0) {
				Integer value = (Integer) histogram.get(new Integer(element+i));
				if (value == null)
					value = new Integer(0);
				avg += smoother.getIncrement(element, i, (value.intValue())); // / max));
			}
		}
		return new SimpleMembership((avg/(2*n+1)) / max);
	}

	public FuzzySet intersection(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet(elementsCount, new SimpleMembership(0));
		//for (int i = 0; i < elementsCount; i++)
		Iterator it = new CombinedIterator(iterator(),set.iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).and(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet(elementsCount, new SimpleMembership(0));
		//for (int i = 0; i < elementsCount; i++)
		Iterator it = new CombinedIterator(iterator(),set.iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).or(set.getMembership(i)).getComponents()));
		}
		return result;
	}

	public FuzzySet complement() {
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).complement().getComponents()));
		return result;
	}
	
	public FuzzySet minus(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet(elementsCount, new SimpleMembership(0));
		Iterator it = new CombinedIterator(iterator(),set.iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).minus(set.getMembership(i)).getComponents()));
		}
		return result;
	}
	
	public FuzzySet iii(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet(elementsCount, new SimpleMembership(0));
		Iterator it = new CombinedIterator(iterator(),set.iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			Membership m1 = getMembership(i);
			Membership m2 = set.getMembership(i);
			result.addMembership(i, 
					new SimpleMembership(m1.and(m2).and(m1.complement().and(m2.complement()))
							.getComponents()));
		}
		return result;
	}
	
	public FuzzySet uiu(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet(elementsCount, new SimpleMembership(0));
		Iterator it = new CombinedIterator(iterator(),set.iterator());
		while (it.hasNext()) {
			int i = ((Integer)it.next()).intValue();
			Membership m1 = getMembership(i);
			Membership m2 = set.getMembership(i);
			result.addMembership(i, 
					new SimpleMembership(m1.or(m2).and(m1.complement().or(m2.complement()))
							.getComponents()));
		}
		return result;
	}

}
