import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.fuzzy.ColorMembership;
import de.berlios.imilarity.image.Color;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.PartOfImage;
import de.berlios.imilarity.measures.FuzzyImageMeasure;
import de.berlios.imilarity.measures.ImageMeasure;
import de.berlios.imilarity.measures.ImageMeasureBase;
import de.berlios.imilarity.measures.M3;

public class MultiresPrint extends ImageMeasureBase {

	private ImageMeasure measure;
	private Aggregator aggregator;
	private SortedSet querySet, targetSet;
	
	private static final int PART_W = 42, PART_H = 42;
	
	
	public MultiresPrint(ImageMeasure measure, Aggregator aggregator) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		if (aggregator == null)
			throw new NullPointerException("aggregator == null");
		this.aggregator = aggregator;
	}
	
	public MultiresPrint(ImageMeasure measure) {
		this(measure, new ArithmeticMean());
	}
	
	
	public void setQuery(Image query) {
		super.setQuery(query);
		System.out.println("Query set:");
		querySet = createPartsSet(query);
		System.out.println();
		System.out.println("Sorted query set:");
		printPartSet(querySet);
		System.out.println();
	}
	
	public void setTarget(Image target) {
		super.setTarget(target);
		System.out.println("Target set:");
		targetSet = createPartsSet(target);
		System.out.println();
		System.out.println("Sorted target set:");
		printPartSet(targetSet);
		System.out.println();
	}
	
	public double getSimilarity() {
		aggregator.clearValues();
		Iterator qIt = querySet.iterator();
		Iterator tIt = targetSet.iterator();
		if (qIt.hasNext() && tIt.hasNext()) {
			Image target = (Image) tIt.next();
			while (qIt.hasNext() && tIt.hasNext()) {
				Image query = (Image) qIt.next();
				measure.setQuery(query);
				measure.setTarget(target);
				double prevSim = measure.getSimilarity();
				double sim = prevSim;
				System.out.println("Sim between " + query + " and " + target + ": " + sim);
				while (prevSim <= sim && tIt.hasNext()) {
					target = (Image) tIt.next();
					measure.setTarget(target);
					prevSim = sim;
					sim = measure.getSimilarity();
					System.out.println("Sim between " + query + " and " + target + ": " + sim);
				}
				aggregator.addValue(prevSim);
				System.out.println("Sim added: " + prevSim);
				System.out.println();
			}
			if (qIt.hasNext()) {
				Image query = (Image) qIt.next();
				measure.setQuery(query);
				measure.setTarget(target);
				double sim = measure.getSimilarity();
				System.out.println("Sim between " + query + " and " + target + ": " + sim);
				aggregator.addValue(sim);
				System.out.println("Sim added: " + sim);
				System.out.println();
			}	
			return aggregator.getAggregatedValue();
		} else
			return 0; 
	}

	public String getDescription() {
		return "Multiresolution " + measure.getDescription();
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
				AvgColorPartOfImage pi = 
					new AvgColorPartOfImage(image,c*PART_W,r*PART_H,PART_W,PART_H);
				result.add(pi);
				System.out.println(pi);
			}
		}
		return result;
	}
	
	private static void printPartSet(SortedSet set) {
		Iterator it = set.iterator();
		for (int i = 1; it.hasNext(); i++)
			System.out.println("" + i + ": " + ((AvgColorPartOfImage)it.next()));
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
			return cm1.compareTo(cm2);
		}
		
		public String toString() {
			double[] comps = getAvgColor().getComponents();
			return "(" + ((int)(comps[0]*255)) + ", " + ((int)(comps[1]*255)) + ", " + ((int)(comps[2]*255)) + ")";
		}
	}

	
	
	public static void main(String[] args) throws IOException {
		MultiresPrint mp = new MultiresPrint(new FuzzyImageMeasure(new M3()));
		mp.setQuery(ImageData.loadFile("/home/klbostee/Images/coil5/obj3__0.png").getRgbImage());
		mp.setTarget(ImageData.loadFile("/home/klbostee/Images/coil5/obj3__270.png").getRgbImage());
		double sim = mp.getSimilarity();
		System.out.println("Global sim: " + sim);
	}
}
