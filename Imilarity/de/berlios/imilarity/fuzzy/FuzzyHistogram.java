package de.berlios.imilarity.fuzzy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.LabImage;
import de.berlios.imilarity.image.quantizers.Quantizer;

public class FuzzyHistogram extends FuzzySetBase {

	private Quantizer quantizer;
	private FuzzySet aggregation; 
	//private SortedSet elements;
	private List elements = new LinkedList();
	
	private Membership[] memberships; // voor caching
	
	public FuzzyHistogram(LabImage image, Quantizer quantizer) {
		if (image == null)
			throw new NullPointerException("image == null");
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.quantizer = quantizer;
		
		//elements = new TreeSet();
		
		int pc = image.getWidth()*image.getHeight();
		SortedSet appearingColors = new TreeSet();
		quantizer.quantize(image);
		for (int i = 0; i < pc; i++) {
			int bin = quantizer.getBin(i);
			//elements.add(new Integer(bin));
			Color c = quantizer.getBinColor(bin);
			appearingColors.add(c);
		}
		Iterator it = appearingColors.iterator();
		if (it.hasNext()) {
			aggregation = new FuzzyColor((Color) it.next(), quantizer);
			while (it.hasNext())
				aggregation = aggregation.union(new FuzzyColor((Color) it.next(), quantizer));
		}
		
		
		// optimalisaties ivm rekentijd:
		
		elements.clear();
		int bc = quantizer.getBinsCount();
		memberships = new Membership[bc];
		for (int i = 0; i < bc; i++) {
			memberships[i] = aggregation.getMembership(i);
			if (memberships[i].abs() > 0)
				elements.add(new Integer(i));
		}
		
	}
	
	public int getElementsCount() {
		return quantizer.getBinsCount();
	}
	
	public Iterator iterator() {
		return elements.iterator();
	}

	public Membership getMembership(int element) {
		//return aggregation.getMembership(element);
		return memberships[element];
	}

}
