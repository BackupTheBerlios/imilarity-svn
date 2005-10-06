/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.image.AggregatedColorImage;
import de.berlios.imilarity.image.ColorImage;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.measures.ColorMeasure;
import de.berlios.imilarity.measures.FastGrayscaleMeasure;
import de.berlios.imilarity.measures.FastGrayscaleMeasureFactory;
import de.berlios.imilarity.measures.GrayscaledColorMeasure;
import de.berlios.imilarity.measures.HomGrayscaleMeasure;
import de.berlios.imilarity.measures.M20;
import de.berlios.imilarity.measures.OH3;
import de.berlios.imilarity.measures.PartGrayscaleMeasure;
import de.berlios.imilarity.measures.ProductGrayscaleMeasure;
import de.berlios.imilarity.measures.ScalingGrayscaleMeasure;
import de.berlios.imilarity.providors.Providor;
import de.berlios.imilarity.util.ArraysBackedList;




/**
 * @author Klaas Bosteels
 */
public class Imilarity {

	// defaults: 
	private Providor providor = null;
	private Aggregator aggregator = new ArithmeticMean();
	private ColorMeasure measure = 
		new GrayscaledColorMeasure(new ScalingGrayscaleMeasure(
				new PartGrayscaleMeasure(new FastGrayscaleMeasureFactory() {
					public FastGrayscaleMeasure createMeasure() {
						return new ProductGrayscaleMeasure(
							new OH3(), new HomGrayscaleMeasure(new M20()));
					}
				})));
	
	private Collection examples = new HashSet(); 
	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	private boolean aggregationCalculated = false;
	ColorImage aggregation = null;
	
	
	public void setProvidor(Providor providor) {
		if (providor == null)
			throw new NullPointerException("providor == null");
		this.providor = providor;
		pages = new ImageData[getPageCount()][getPageSize()];
		for (int i = 0; i < pages.length; i++)
			pages[i] = null;
		pageLoaded = new boolean[getPageCount()];
	}
	
	public void setAggregator(Aggregator aggregator) {
		if (aggregator == null)
			throw new NullPointerException("aggregator == null");
		this.aggregator = aggregator;
	}
	
	public void setMeasure(ColorMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		if (aggregationCalculated)
			measure.setImage(aggregation);
	}
	
	
	
	public void loadPage(int page) throws IOException {
		getPage(page);
	}
	
	public void loadImages() throws IOException {
		getImages();
	}
	
	
	public void stopLoading() {
		for (int i = 0; i < pageLoaded.length; i++) {
			pageLoaded[i] = true;
			if (pages[i] == null) {
				pages[i] = new ImageData[getPageSize()];
				for (int j = 0; j < pages[i].length; j++)
					pages[i][j] = null;
			}
		}
	}
	
	
	public boolean isPageLoaded(int page) {
		return pageLoaded[page-1];
	}
	
	public boolean areImagesLoaded() {
		for (int i = 0; i < pageLoaded.length; i++)
			if (!pageLoaded[i])
				return false;
		return true;
	}
	
	
	public int getPageSize() {
		if (providor == null) 
			return 0;
		return providor.getPageSize();
	}
	
	public int getPageCount() {
		if (providor == null)
			return 0;
		return providor.getPageCount();
	}
	
	public ImageData[] getPage(int page) throws IOException {
		if (providor == null)
			return null;
		if (page < 1 || page > getPageCount())
			throw new IOException("wrong page number");
		if (!pageLoaded[page-1]) {
			pageLoaded[page-1] = true;
			pages[page-1] = providor.getPage(page);
		}
		// Als deze methode in twee aparte draden uitgevoerd wordt dan kan het 
		// voorkomen dat 'pages[x]' nog gelijk is aan 'null', terwijl de inhoud
		// van 'pages[x]' wel al berekend wordt. In dat geval moet er gewacht worden...
		else while (pages[page-1] == null);
		return pages[page-1];
	}
	
	public ImageData[] getImages() throws IOException {
		if (providor == null)
			return null;
		int ps = getPageSize(), pc = getPageCount();
		ImageData[] images = new ImageData[ps * pc];
		for (int i = 0; i < pc; i++) {
			ImageData[] pageImages = getPage(i);
			for (int j = 0; j < pageImages.length; j++)
				images[i+j] = pageImages[j];
		}
		return images;
	}
	
	
	
	public void addExample(ImageData image) {
		if (image != null) {
			examples.add(image);
			aggregationCalculated = false;
		}
	}
	
	public void removeExample(ImageData image) {
		if (image != null) {
			examples.remove(image);
			aggregationCalculated = false;
		}
	}
	
	public boolean containsExample(ImageData image) {
		return examples.contains(image);
	}
	
	public boolean containsExamples() {
		return examples.isEmpty();
	}
	
	public void clearExamples() {
		examples.clear();
		aggregationCalculated = false;
	}
	
	public ImageData[] getExamples() {
		ImageData[] result = new ImageData[examples.size()];
		Iterator it = examples.iterator();
		for (int i = 0; it.hasNext(); i++)
			result[i] = (ImageData) it.next();
		return result;
	}
	
	private static final Comparator comparator = new Comparator() {
		public int compare(Object arg0, Object arg1) {
			if (arg0 == null && arg1 == null)
				return 0;
			if (arg0 == null)
				return 1;
			if (arg1 == null)
				return -1;
			return ((Comparable)arg0).compareTo(arg1);
		}
	};
	
	long totalTime = 0; // profiling
	
	public void reorderPage(int page) {
		if (providor == null)
			return;
		
		long millis = System.currentTimeMillis();
		
		if (!aggregationCalculated) {
			aggregationCalculated = true;
			ColorImage[] scis = new ColorImage[examples.size()];
			Iterator it = examples.iterator();
			for (int i = 0; i < scis.length && it.hasNext(); i++)
				scis[i] = ((ImageData) it.next()).getColorImage();
			aggregation = new AggregatedColorImage(scis, aggregator);
			measure.setImage(aggregation);
		}
		for (int i = 0; i < pages[page-1].length; i++)
			if (pages[page-1][i] != null)
				pages[page-1][i].setSimilarity
					(measure.similarity(pages[page-1][i].getColorImage()));
		Arrays.sort(pages[page-1], comparator);
		
		totalTime += System.currentTimeMillis() - millis;
	}
	
	public void mergeReorderedPages() {
		if (providor == null)
			return;
		
		long millis = System.currentTimeMillis();
		
		ImageData[][] arrays = new ImageData[getPageCount()][];
		for (int i = 0; i < getPageCount(); i++)
			arrays[i] = pages[i];
		List l = new ArraysBackedList(arrays, getPageSize());
		Collections.sort(l, comparator);
		
		totalTime += System.currentTimeMillis() - millis; 
		System.out.println("Reordered in " + totalTime + " ms using '" + measure + "'."); 
		totalTime = 0;
	}
	
	public void reorderImages() {
		for (int i = 1; i <= getPageCount(); i++)
			reorderPage(i);
		mergeReorderedPages();
	}
}
