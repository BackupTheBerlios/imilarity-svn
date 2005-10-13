package de.berlios.imilarity.measures;


import java.util.Comparator;
import java.util.HashMap;

import de.berlios.imilarity.fuzzy.ArrayFuzzySet;
import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.Membership;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ColorQuantizer;
import de.berlios.imilarity.image.NeuQuant;


public class FuzzyQuantizedImageMeasure extends ImageMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	
	public FuzzyQuantizedImageMeasure(FuzzyMeasure fuzzyMeasure) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		this.fuzzyMeasure = fuzzyMeasure;
	}
	
	public double getSimilarity() {
		FuzzySet queryColors = calculateColors(getQuery());
		FuzzySet targetColors = calculateColors(getTarget());
		fuzzyMeasure.setQuery(queryColors);
		fuzzyMeasure.setTarget(targetColors);
		return fuzzyMeasure.getSimilarity();
	}
	
	private FuzzySet calculateColors(Image image) {
		ColorQuantizer cq = new NeuQuant(30,8);
		cq.quantize(image);
		ArrayFuzzySet colors = new ArrayFuzzySet();
		
		final HashMap freqs = new HashMap();
		for (int i = 0; i < cq.getColorCount(); i++) {
			int[] intColor = cq.getColor(i);
			double[] color = new double[intColor.length];
			// normaliseren:
			for (int j = 0; j < intColor.length; j++)
				color[j] = intColor[j] * 1.0 / 255;
			//System.out.println("color " + i + ": " + color[0] + "," + color[1] + "," + color[2]);
			// toevoegen:
			Membership m = new EqVectorMembership(color);
			colors.addMembership(m);
			freqs.put(m, new Frequency());
 		}
		
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			int[] intColor = cq.getPixelColor(i);
			double[] color = new double[intColor.length]; 
			// normaliseren:
			for (int j = 0; j < intColor.length; j++)
				color[j] = intColor[j] * 1.0 / 255;
			//System.out.println("pixelcolor: " + color[0] + "," + color[1] + "," + color[2]);
			// incrementeren:
			((Frequency)freqs.get(new EqVectorMembership(color))).value++;
		}
		colors.sort(new Comparator() {
			public int compare(Object arg0, Object arg1) {
				//Membership m1 = (Membership) arg0;
				//Membership m2 = (Membership) arg1;
				//return (new Double(m1.abs())).compareTo(new Double(m2.abs()));
				Frequency f1 = (Frequency) freqs.get(arg0);
				Frequency f2 = (Frequency) freqs.get(arg1);
				return (new Double(f1.value)).compareTo(new Double(f2.value));
			}
		});
		return colors;
	}
	
	private class EqVectorMembership extends Membership {
		public EqVectorMembership(double[] components) {
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
		return "Fuzzy Quantized using " + fuzzyMeasure.getDescription();
	}
}
