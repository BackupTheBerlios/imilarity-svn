package de.berlios.imilarity.measures;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.fuzzy.ColorMembership;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.PartOfImage;

public class PartsImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	private Aggregator aggregator;
	private SortedSet querySet, targetSet;
	
	private static final int PART_W = 8, PART_H = 8;
	
	
	public PartsImageMeasure(ImageMeasure measure, Aggregator aggregator) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		if (aggregator == null)
			throw new NullPointerException("aggregator == null");
		this.aggregator = aggregator;
	}
	
	public PartsImageMeasure(ImageMeasure measure) {
		this(measure, new ArithmeticMean());
	}
	
	
	public void setQuery(Image query) {
		super.setQuery(query);
		querySet = createPartsSet(query);
	}
	
	public void setTarget(Image target) {
		super.setTarget(target);
		targetSet = createPartsSet(target);
	}
	
	public double getSimilarity() { 
		double sim1 = calculateSimilarity(querySet, targetSet);
		double sim2 = calculateSimilarity(targetSet, querySet);
		return (sim1 + sim2) / 2;
	}
	
	private double calculateSimilarity(SortedSet a, SortedSet b) { 
		aggregator.clearValues();
		Iterator qIt = a.iterator();
		Iterator tIt = b.iterator();
		if (qIt.hasNext() && tIt.hasNext()) {
			Image target = (Image) tIt.next();
			double prevSim = 0, sim = 0;
			while (qIt.hasNext() && tIt.hasNext()) {
				Image query = (Image) qIt.next();
				measure.setQuery(query);
				measure.setTarget(target);
				prevSim = 0;
				sim = measure.getSimilarity();
				while (prevSim < sim && tIt.hasNext()) {
					target = (Image) tIt.next();
					measure.setTarget(target);
					prevSim = sim;
					sim = measure.getSimilarity();
				}
				aggregator.addValue(Math.max(prevSim,sim));
			}
			if (prevSim >= sim && qIt.hasNext()) {
				Image query = (Image) qIt.next();
				measure.setQuery(query);
				measure.setTarget(target);
				sim = measure.getSimilarity();
				aggregator.addValue(sim);
			}	
			return aggregator.getAggregatedValue();
		} else
			return 0; 
	}

	public String getDescription() {
		return "Parts using " + measure.getDescription();
	}

	
	
	private static SortedSet createPartsSet(Image image) {
		SortedSet result = new TreeSet();
		// breedte en hoogte worden naar beneden afgerond:
		int width = (image.getWidth() / PART_W) * PART_W;
		int height = (image.getHeight() / PART_H) * PART_H;
		int pc = width * height;
		for (int i = 0; i < pc; i++) {
			int partX = (i % width) % PART_W; // = x % PART_W
			int partY = (i / width) % PART_H; // = y % PART_H
			if (partX == 0 && partY == 0) {
				int c = (i % width) / PART_W; // = x / PART_W
				int r = (i / width) / PART_H; // = y / PART_H
				result.add(new AvgColorPartOfImage(image,c*PART_W,r*PART_H,PART_W,PART_H));
			}
		}
		return result;
	}
	
	private static class AvgColorPartOfImage extends PartOfImage implements Comparable {
		
		public AvgColorPartOfImage(Image image, int x, int y, int w, int h) {
			super(image, x, y, w, h);
		}
		
		private Color avgColor = null;
		
		public Color getAvgColor() {
			if (avgColor == null) {
				int pc = getWidth() * getHeight();
				double[] comps = new double[getColorComponentsCount()];
				for (int i = 0; i < pc; i++) {
					double[] c = getColor(i).getComponents();
					for (int j = 0; j < c.length; j++)
						comps[j] += c[j];
				}
				for (int i = 0; i < getColorComponentsCount(); i++)
					comps[i] /= pc;
				avgColor = new Color(comps);
			}
			return avgColor;
		}

		public int compareTo(Object arg0) {
			ColorMembership cm1 = new ColorMembership(getAvgColor());
			ColorMembership cm2 = new ColorMembership(((AvgColorPartOfImage)arg0).getAvgColor());
			return -cm1.compareTo(cm2);
		}
	}
}
