package de.berlios.imilarity.fuzzy;

import java.util.Arrays;

import de.berlios.imilarity.image.GrayscaleImage;

public class FuzzyGrayscaleHistogram implements FuzzySet {

	private int[] histogram = new int[256];
	private int max = 0;
	
	public FuzzyGrayscaleHistogram(GrayscaleImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		if (image.getColorComponentsCount() != 1)
			throw new IllegalArgumentException("image must have 1 color component");
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			int value = (int) (image.getColor(i).getComponents()[0]*255);
			histogram[value]++;
			if (histogram[value] > max) 
				max = histogram[value];
		}
		Arrays.sort(histogram);
	}
	
	public FuzzyGrayscaleHistogram(int[] histogram, int max) {
		if (histogram == null)
			throw new NullPointerException("histogram == null");
		if (histogram.length != 256)
			throw new IllegalArgumentException("histogram length must be 256");
		this.histogram = histogram;
		this.max = max;
	}
	
	public int getElementsCount() {
		return 256;
	}

	public Membership getMembership(int element) {
		return new Membership(histogram[element] * 1.0 / max);
	}

}
