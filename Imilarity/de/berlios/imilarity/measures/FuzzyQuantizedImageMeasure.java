package de.berlios.imilarity.measures;


import java.util.Comparator;
import java.util.HashMap;

import de.berlios.imilarity.fuzzy.ArrayFuzzySet;
import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.Membership;
import de.berlios.imilarity.fuzzy.SimpleMembership;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.quantizers.ColorQuantizer;
import de.berlios.imilarity.image.quantizers.NeuQuant;


public class FuzzyQuantizedImageMeasure extends ImageMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	private ColorQuantizer quantizer;
	private int colorCount; 
	
	public FuzzyQuantizedImageMeasure(FuzzyMeasure fuzzyMeasure, ColorQuantizer quantizer,
			int colorCount) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.fuzzyMeasure = fuzzyMeasure;
		this.quantizer = quantizer;
		this.colorCount = colorCount;
	}
	
	public FuzzyQuantizedImageMeasure(FuzzyMeasure fuzzyMeasure, ColorQuantizer quantizer) {
		this(fuzzyMeasure, quantizer, quantizer.getColorCount());
	}
	
	public FuzzyQuantizedImageMeasure(FuzzyMeasure fuzzyMeasure) {
		this(fuzzyMeasure, new NeuQuant(30,8));
	}
	
	
	public double getSimilarity() {
		FuzzySet queryColors = calculateColors(getQuery());
		FuzzySet targetColors = calculateColors(getTarget());
		fuzzyMeasure.setQuery(queryColors);
		fuzzyMeasure.setTarget(targetColors);
		return fuzzyMeasure.getSimilarity();
	}
	
	private FuzzySet calculateColors(Image image) {
		quantizer.quantize(image);
		ArrayFuzzySet colors = new ArrayFuzzySet();
		//List colors = new ArrayList();
		
		final HashMap freqs = new HashMap();
		for (int i = 0; i < quantizer.getColorCount(); i++) {
			int[] intColor = quantizer.getColor(i);
			double[] color = new double[intColor.length];
			// normaliseren:
			for (int j = 0; j < intColor.length; j++)
				color[j] = intColor[j] * 1.0 / 255;
			// toevoegen:
			Membership m = new EqMembership(color);
			colors.addMembership(m);
			//colors.add(m);
			freqs.put(m, new Frequency());
 		}
		
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			int[] intColor = quantizer.getPixelColor(i);
			double[] color = new double[intColor.length]; 
			// normaliseren:
			for (int j = 0; j < intColor.length; j++)
				color[j] = intColor[j] * 1.0 / 255;
			// incrementeren:
			((Frequency)freqs.get(new EqMembership(color))).value++;
		}
		colors.sort(new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Frequency f1 = (Frequency) freqs.get(arg0);
				Frequency f2 = (Frequency) freqs.get(arg1);
				return (new Double(f2.value)).compareTo(new Double(f1.value));
			}
		});
		return colors.head(colorCount);
	}
	
	private class EqMembership extends SimpleMembership {
		public EqMembership(double[] components) {
			super(components);
		}
		public boolean equals(Object o) {
			return equals((Membership) o);
		}
		public int hashCode() {
			return (int) (abs() * 10000);
		}
	}
	
	private static class Frequency {
		public int value = 0;
	}

	
	public String getDescription() {
		return "Fuzzy Quantized using " + fuzzyMeasure.getDescription() + " and "
			+ quantizer.getDescription();
	}
}
