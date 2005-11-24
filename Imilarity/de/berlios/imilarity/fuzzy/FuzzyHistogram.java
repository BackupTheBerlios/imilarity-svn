package de.berlios.imilarity.fuzzy;

import java.util.HashMap;
import java.util.Map;


import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.smoothers.DefaultSmoother;
import de.berlios.imilarity.smoothers.Smoother;

public class FuzzyHistogram extends FuzzySetBase {

	private Map histogram;
	private int elementsCount = 0;
	private double max = 0.0;
	private Smoother smoother;
	
	
	public FuzzyHistogram(Image image, int[] binsCounts) {
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
		
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			int value = 
				Math.min((int)(image.getColor(i).getComponents()[0]*binsCounts[i]),binsCounts[i]-1);
			Integer index = new Integer(value);
			Integer prev = (Integer)histogram.get(index);
			if (prev == null) prev = new Integer(0);
			
			int newValue = prev.intValue()+1;
			histogram.put(index, new Integer(newValue));
			if (newValue > max) max = newValue;
		}
	}
	
	public FuzzyHistogram(Map histogram, int elementsCount, Smoother smoother) {
		if (histogram == null)
			throw new NullPointerException("histogram == null");
		this.histogram = histogram;
		if (smoother == null)
			throw new IllegalArgumentException("smoother == null");
		this.smoother = smoother;
		this.elementsCount = elementsCount;
		
		max = 0.0;
		for (int j = 0; j < elementsCount; j++) {
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
	}
	
	public FuzzyHistogram(Map histogram, int elementsCount) {
		this(histogram, elementsCount, new DefaultSmoother());
	}
	
	
	
	public int getElementsCount() {
		return elementsCount;
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
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).and(set.getMembership(i)).getComponents()));
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).or(set.getMembership(i)).getComponents()));
		return result;
	}

	public FuzzySet complement() {
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).complement().getComponents()));
		return result;
	}

}
