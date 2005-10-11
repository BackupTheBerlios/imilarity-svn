package de.berlios.imilarity.fuzzy;

import java.util.Arrays;

import de.berlios.imilarity.image.GrayscaleImage;

public class FuzzyGrayscaleHistogram implements FuzzySet {

	private int[] histogram = new int[256];
	private int max = 0;
	
	public FuzzyGrayscaleHistogram(GrayscaleImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < histogram.length; i++)
			System.out.println("histogram[i] = " + histogram[i]);
		for (int i = 0; i < pc; i++) {
			int value = image.getGrayscaleValue(i);
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

	public double getMembership(int element) {
		return histogram[element] * 1.0 / max;
	}

}
