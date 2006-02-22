/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.HashMap;


import de.berlios.imilarity.fuzzy.PseudoFuzzyHistogram;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.quantizers.Quantizer;
import de.berlios.imilarity.image.quantizers.UniformQuantizer;
import de.berlios.imilarity.smoothers.DefaultSmoother;
import de.berlios.imilarity.smoothers.Smoother;


public class PseudoFuzzyHistogramImageMeasure extends ImageMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	
	private Quantizer quantizer;
	
	private int targetPc = 0, queryPc = 0; // pc = pixelcount
	private HashMap queryHistogram, targetHistogram;
	private int queryHistLength, targetHistLength;
	private Smoother smoother;
	
	
	public PseudoFuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, Quantizer quantizer, 
			Smoother smoother) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.fuzzyMeasure = fuzzyMeasure;
		queryHistogram = new HashMap();
		targetHistogram = new HashMap();
		this.quantizer = quantizer;
		if (smoother == null)
			throw new NullPointerException("smoother == null");
		this.smoother = smoother;
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
		queryHistLength = quantizer.getBinsCount();
		queryHistogram.clear();
		for (int i = 0; i < queryPc; i++) {
			Integer index = new Integer(quantizer.getBin(i));
			Integer oldValue = (Integer) queryHistogram.get(index);
			if (oldValue == null) oldValue = new Integer(0);
			Integer newValue = new Integer(oldValue.intValue()+1);
			queryHistogram.put(index, newValue);
		}
		fuzzyMeasure.setQuery
			(new PseudoFuzzyHistogram(queryHistogram, queryHistLength, smoother));
	}
	
	public void setTarget(Image target) {
		super.setTarget(target);
		targetPc = target.getWidth() * target.getHeight();
		quantizer.quantize(getTarget());
		targetHistLength = quantizer.getBinsCount();
		targetHistogram.clear();
		for (int i = 0; i < targetPc; i++) {
			Integer index = new Integer(quantizer.getBin(i));
			Integer oldValue = (Integer) targetHistogram.get(index);
			if (oldValue == null) oldValue = new Integer(0);
			Integer newValue = new Integer(oldValue.intValue()+1);
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
		buf.append(" and ");
		buf.append(smoother.getDescription());
		return buf.toString();
	}

}
