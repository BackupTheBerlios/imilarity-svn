package de.berlios.imilarity.fuzzy;

import de.berlios.imilarity.image.LabImage;
import de.berlios.imilarity.image.quantizers.Quantizer;

public class FuzzyHistogram extends FuzzySetBase {

	private Quantizer quantizer;
	private FuzzySet aggregation; 
	
	public FuzzyHistogram(LabImage image, Quantizer quantizer) {
		if (image == null)
			throw new NullPointerException("image == null");
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.quantizer = quantizer;
		
		quantizer.quantize(image);
		int pc = image.getWidth()*image.getHeight();
		int bc = quantizer.getBinsCount();
		boolean[] appears = new boolean[bc];
		for (int i = 0; i < pc; i++)
			appears[quantizer.getBin(i)] = true;
		int start = 0;
		while (!appears[start]) start++;
		aggregation = new FuzzyColor(quantizer.getBinColor(start), quantizer);
		for (int i = start+1; i < bc; i++)
			if (appears[i])
				aggregation = aggregation.union(new FuzzyColor(quantizer.getBinColor(i), quantizer));
	}
	
	public int getElementsCount() {
		return quantizer.getBinsCount();
	}

	public Membership getMembership(int element) {
		return aggregation.getMembership(element);
	}

}
