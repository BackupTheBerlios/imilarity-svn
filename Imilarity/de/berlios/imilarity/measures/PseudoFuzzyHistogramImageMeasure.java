/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.HashMap;


import de.berlios.imilarity.fuzzy.PseudoFuzzyHistogram;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.quantizers.Quantizer;
import de.berlios.imilarity.image.quantizers.UniformQuantizer;
import de.berlios.imilarity.scales.DefaultScale;
import de.berlios.imilarity.scales.Scale;
import de.berlios.imilarity.smoothers.DefaultSmoother;
import de.berlios.imilarity.smoothers.Smoother;


public class PseudoFuzzyHistogramImageMeasure extends ImageMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	
	private Quantizer quantizer;
	
	private int targetPc = 0, queryPc = 0; // pc = pixelcount
	private HashMap queryHistogram, targetHistogram;
	private int queryHistLength, targetHistLength;
	private Smoother smoother;
	private Scale scale;
	
	
	public PseudoFuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, Quantizer quantizer, 
			Smoother smoother, Scale scale) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		this.fuzzyMeasure = fuzzyMeasure;
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.quantizer = quantizer;
		if (smoother == null)
			throw new NullPointerException("smoother == null");
		this.smoother = smoother;
		if (scale == null)
			throw new NullPointerException("scale == null");
		this.scale = scale;
		queryHistogram = new HashMap();
		targetHistogram = new HashMap();
	}
	
	public PseudoFuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, Quantizer quantizer, 
			Smoother smoother) {
		this(fuzzyMeasure, quantizer, smoother, new DefaultScale());
	}
	
	public PseudoFuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, Quantizer quantizer) {
		this(fuzzyMeasure, quantizer, new DefaultSmoother());
	}
	
	public PseudoFuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, int[] binsCounts, 
			Smoother smoother) {
		this(fuzzyMeasure, new UniformQuantizer(binsCounts), smoother);
	}
	
	public PseudoFuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, int[] binsCounts) {
		this(fuzzyMeasure, new UniformQuantizer(binsCounts));
	}
	
	
	
	public void setQuery(Image query) {
		super.setQuery(query);
		queryPc = query.getWidth() * query.getHeight();
		quantizer.quantize(getQuery());
		scale.setImage(query);
		queryHistLength = quantizer.getBinsCount();
		queryHistogram.clear();
		for (int i = 0; i < queryPc; i++) {
			Integer index = new Integer(quantizer.getBin(i));
			Double oldValue = (Double) queryHistogram.get(index);
			if (oldValue == null) oldValue = new Double(0);
			Double newValue = new Double(oldValue.intValue()+scale.getWeight(i));
			queryHistogram.put(index, newValue);
		}
		fuzzyMeasure.setQuery
			(new PseudoFuzzyHistogram(queryHistogram, queryHistLength, smoother));
	}
	
	public void setTarget(Image target) {
		super.setTarget(target);
		targetPc = target.getWidth() * target.getHeight();
		quantizer.quantize(getTarget());
		scale.setImage(target);
		targetHistLength = quantizer.getBinsCount();
		targetHistogram.clear();
		for (int i = 0; i < targetPc; i++) {
			Integer index = new Integer(quantizer.getBin(i));
			Double oldValue = (Double) targetHistogram.get(index);
			if (oldValue == null) oldValue = new Double(0);
			Double newValue = new Double(oldValue.intValue()+scale.getWeight(i));
			targetHistogram.put(index, newValue);
		}
		fuzzyMeasure.setTarget
			(new PseudoFuzzyHistogram(targetHistogram, targetHistLength, smoother));
	}

	
	public double getSimilarity() {
		return fuzzyMeasure.getSimilarity();
	}

	
	public String getDescription() {
		StringBuffer buf = new StringBuffer();
		buf.append("Pseudo Fuzzy Histogram with ");
		buf.append(quantizer.getDescription());
		buf.append(" quantizer using ");
		buf.append(fuzzyMeasure.getDescription());
		buf.append(", ");
		buf.append(smoother.getDescription());
		buf.append(" and ");
		buf.append(scale.getDescription());
		return buf.toString();
	}

}
