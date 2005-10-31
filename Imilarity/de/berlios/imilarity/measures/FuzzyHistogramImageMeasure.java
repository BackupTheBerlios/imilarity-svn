/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import java.util.HashMap;

import de.berlios.imilarity.fuzzy.FuzzyHistogram;
import de.berlios.imilarity.image.Image;


public class FuzzyHistogramImageMeasure extends ImageMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	
	private int targetPc = 0, queryPc = 0; // pc = pixelcount
	private HashMap queryHistogram, targetHistogram;
	private int queryHistLength, targetHistLength;
	private int[] binsCounts = new int[] { 256 };
	
	
	public FuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, int[] binsCounts) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		if (binsCounts == null)
			throw new NullPointerException("binsCounts == null");
		this.fuzzyMeasure = fuzzyMeasure;
		queryHistogram = new HashMap();
		targetHistogram = new HashMap();
		this.binsCounts = binsCounts;
	}
	
	public FuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure, int binsCount) {
		this(fuzzyMeasure, new int[] { binsCount });
	}
	
	
	
	public void setQuery(Image query) {
		if (query.getColorComponentsCount() != binsCounts.length)
			throw new IllegalArgumentException("query must have " + binsCounts.length 
					+ " components");
		super.setQuery(query);
		queryPc = query.getWidth() * query.getHeight();
		queryHistLength = binsCounts[0];
		for (int i = 1; i < query.getColorComponentsCount(); i++)
			queryHistLength *= binsCounts[i];
	}
	
	public void setTarget(Image target) {
		if (target.getColorComponentsCount() != binsCounts.length)
			throw new IllegalArgumentException("target must have " + binsCounts.length
					+ " components");
		super.setTarget(target);
		targetPc = target.getWidth() * target.getHeight();
		targetHistLength = binsCounts[0];
		for (int i = 1; i < target.getColorComponentsCount(); i++)
			targetHistLength *= binsCounts[i];
	}

	
	public double getSimilarity() {
		queryHistogram.clear();
		targetHistogram.clear();
		int queryMax = 0, targetMax = 0;
		for (int i = 0; i < queryPc; i++) {
			double[] comps = getQuery().getColor(i).getComponents();
			int v1 = (int) (comps[0]*(binsCounts[0]-1));
			int factor = 1;
			for (int j = 1; j < comps.length; j++) {
				factor *= binsCounts[j];
				v1 += factor * (int)(comps[j]*(binsCounts[j]-1));
			}
			
			Integer index = new Integer(v1);
			Integer prev = (Integer)queryHistogram.get(index);
			if (prev == null) prev = new Integer(0);
			
			int newValue = prev.intValue()+1;
			queryHistogram.put(index, new Integer(newValue));
			if (newValue > queryMax) queryMax = newValue;
		}
		for (int i = 0; i < targetPc; i++) {
			double[] comps = getTarget().getColor(i).getComponents(); 
			int v2 = (int) (comps[0]*(binsCounts[0]-1));
			int factor = 1;
			for (int j = 1; j < comps.length; j++) {
				factor *= binsCounts[j];
				v2 += factor * (int)(comps[j]*(binsCounts[j]-1));
			}
			
			Integer index = new Integer(v2);
			Integer prev = (Integer)targetHistogram.get(index);
			if (prev == null) prev = new Integer(0);
			
			int newValue = prev.intValue()+1;
			targetHistogram.put(index, new Integer(newValue));
			if (newValue > targetMax) targetMax = newValue;
		}
		
		fuzzyMeasure.setQuery
			(new FuzzyHistogram(queryHistogram, queryMax, queryHistLength));
		fuzzyMeasure.setTarget
			(new FuzzyHistogram(targetHistogram, targetMax, targetHistLength));
		return fuzzyMeasure.
		getSimilarity();
	}

	
	public String getDescription() {
		StringBuffer buf = new StringBuffer();
		buf.append("Fuzzy Histogram with ");
		buf.append("" + binsCounts[0]);
		for (int i = 1; i < binsCounts.length; i++)
			buf.append("x" + binsCounts[i]);
		buf.append(" bins using ");
		buf.append(fuzzyMeasure.getDescription());
		return buf.toString();
	}

}
