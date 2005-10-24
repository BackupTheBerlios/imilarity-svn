package de.berlios.imilarity.fuzzy;

import java.util.HashMap;
import java.util.Map;

import de.berlios.imilarity.image.Image;

public class FuzzyHistogram extends FuzzySetBase {

	private Map histogram;
	private int elementsCount = 0, max = 0;
	
	public FuzzyHistogram(Image image) {
		if (image == null)
			throw new NullPointerException("image == null");
		if (image.getColorComponentsCount() != 1)
			throw new IllegalArgumentException("image must have 1 color component");
		
		histogram = new HashMap();
		elementsCount = 256;
		for (int i = 1; i < image.getColorComponentsCount(); i++)
			elementsCount *= 256;
		
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			int value = (int) (image.getColor(i).getComponents()[0]*255);
			Integer index = new Integer(value);
			Integer prev = (Integer)histogram.get(index);
			if (prev == null) prev = new Integer(0);
			
			int newValue = prev.intValue()+1;
			histogram.put(index, new Integer(newValue));
			if (newValue > max) max = newValue;
		}
	}
	
	public FuzzyHistogram(Map histogram, int max, int elementsCount) {
		if (histogram == null)
			throw new NullPointerException("histogram == null");
		this.histogram = histogram;
		this.max = max;
		this.elementsCount = elementsCount;
	}
	
	public int getElementsCount() {
		return elementsCount;
	}

	public Membership getMembership(int element) {
		Integer value = (Integer) histogram.get(new Integer(element));
		if (value == null)
			value = new Integer(0);
		return new SimpleMembership(value.intValue() * 1.0 / max);
	}

	public FuzzySet intersection(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).and(set.getMembership(i)).abs()));
		return result;
	}

	public FuzzySet union(FuzzySet set) {
		if (set.getElementsCount() != elementsCount)
			throw new IllegalArgumentException("not the same elements count");
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).or(set.getMembership(i)).abs()));
		return result;
	}

	public FuzzySet complement() {
		HashedFuzzySet result = new HashedFuzzySet();
		for (int i = 0; i < elementsCount; i++)
			result.addMembership(i, 
					new SimpleMembership(getMembership(i).complement().abs()));
		return result;
	}

}