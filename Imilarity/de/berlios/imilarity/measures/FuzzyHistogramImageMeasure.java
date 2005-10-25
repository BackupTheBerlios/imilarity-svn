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
	
	
	public FuzzyHistogramImageMeasure(FuzzyMeasure fuzzyMeasure) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		this.fuzzyMeasure = fuzzyMeasure;
		queryHistogram = new HashMap();
		targetHistogram = new HashMap();
	}
	
	public void setQuery(Image query) {
		super.setQuery(query);
		queryPc = query.getWidth() * query.getHeight();
		queryHistLength = 256;
		for (int i = 1; i < query.getColorComponentsCount(); i++)
			queryHistLength *= 256;
	}
	
	public void setTarget(Image target) {
		super.setTarget(target);
		targetPc = target.getWidth() * target.getHeight();
		targetHistLength = 256;
		for (int i = 1; i < target.getColorComponentsCount(); i++)
			targetHistLength *= 256;
	}

	
	public double getSimilarity() {
		queryHistogram.clear();
		targetHistogram.clear();
		int queryMax = 0, targetMax = 0;
		for (int i = 0; i < queryPc; i++) {
			double[] comps = getQuery().getColor(i).getComponents();
			int v1 = (int) (comps[0]*255);
			int factor = 1;
			for (int j = 1; j < comps.length; j++) {
				factor *= 256;
				v1 += factor * (int)(comps[j]*255);
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
			int v2 = (int) (comps[0]*255);
			int factor = 1;
			for (int j = 1; j < comps.length; j++) {
				factor *= 256;
				v2 += factor * (int)(comps[j]*255);
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
		return "Fuzzy Histogram using " + fuzzyMeasure.getDescription();
	}

}
