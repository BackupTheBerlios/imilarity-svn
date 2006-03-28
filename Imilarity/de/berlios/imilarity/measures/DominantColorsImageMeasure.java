package de.berlios.imilarity.measures;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.quantizers.Quantizer;
import de.berlios.imilarity.image.quantizers.NeuQuant;


public class DominantColorsImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	private Quantizer quantizer;
	private int colorCount; 
	
	public DominantColorsImageMeasure(ImageMeasure measure, Quantizer quantizer,
			int colorCount) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.measure = measure;
		this.quantizer = quantizer;
		this.colorCount = colorCount;
	}
	
	public DominantColorsImageMeasure(ImageMeasure measure, Quantizer quantizer) {
		this(measure, quantizer, quantizer.getBinsCount());
	}
	
	public DominantColorsImageMeasure(ImageMeasure measure) {
		this(measure, new NeuQuant(30,8));
	}
	
	
	public void setQuery(Image image) {
		Image queryColors = calculateIntermediateImage(image);
		measure.setQuery(queryColors);
		super.setQuery(queryColors);
	}
	
	public void setTarget(Image image) {
		Image targetColors = calculateIntermediateImage(image);
		measure.setTarget(targetColors);
		super.setTarget(targetColors);
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}
	
	private Image calculateIntermediateImage(Image image) {
		BufferedImage res = new BufferedImage(colorCount, 1, BufferedImage.TYPE_INT_RGB);
		List colors = calculateColors(image);
		int counter = 0;
		Iterator it = colors.iterator();
		while (it.hasNext()) {
			double[] comps = ((Color) it.next()).getComponents();
			int rgb = 
				(((int)(comps[0]*255) & 0xFF) << 16) |
				(((int)(comps[1]*255) & 0xFF) << 8)  |
				(((int)(comps[2]*255) & 0xFF) << 0);
			res.setRGB(counter++, 0, rgb);
		}
		return (new ImageData(res, "intermediate image", null)).getRgbImage();
	}
		
	private List calculateColors(Image image) {
		quantizer.quantize(image);
		int bc = quantizer.getBinsCount();
		List colors = new ArrayList();
		
		final HashMap freqs = new HashMap();
		for (int i = 0; i < bc; i++) {
			Color color = quantizer.getBinColor(i);
			colors.add(color);
			freqs.put(color, new Frequency());
 		}
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			Color color = quantizer.getBinColor(quantizer.getBin(i));
			((Frequency)freqs.get(color)).value++;
		}
		Collections.sort(colors, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Frequency f1 = (Frequency) freqs.get(arg0);
				Frequency f2 = (Frequency) freqs.get(arg1);
				return (new Double(f2.value)).compareTo(new Double(f1.value));
			}
		});
		return colors.subList(0, colorCount);
	}
	
	private static class Frequency {
		public int value = 0;
	}

	
	public String getDescription() {
		return "Dominant Colors using " + measure.getDescription() + " and " + colorCount + " colors from "
			+ quantizer.getDescription() + " quantizer";
	}
}
