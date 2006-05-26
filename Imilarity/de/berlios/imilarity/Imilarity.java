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
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.measures.ImageMeasure;
import de.berlios.imilarity.providers.Provider;
import de.berlios.imilarity.util.ArraysBackedList;




/**
 * @author Klaas Bosteels
 */
public class Imilarity {

	// defaults: 
	private Provider provider = null;
	private Aggregator aggregator = new ArithmeticMean();
	private ImageMeasure measure = null;

	
	private Collection examples = new HashSet(); 
	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	
	public void setProvider(Provider provider) {
		if (provider == null)
			throw new NullPointerException("provider == null");
		this.provider = provider;
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
	
	public void setMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
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
		if (provider == null) 
			return 0;
		return provider.getPageSize();
	}
	
	public int getPageCount() {
		if (provider == null)
			return 0;
		return provider.getPageCount();
	}
	
	public ImageData[] getPage(int page) throws IOException {
		if (provider == null)
			return null;
		if (page < 1 || page > getPageCount())
			throw new IOException("wrong page number");
		synchronized (provider) {
			if (!pageLoaded[page-1]) {
				pageLoaded[page-1] = true;
				pages[page-1] = provider.getPage(page);
			}
		}
		return pages[page-1];
	}
	
	public ImageData[] getImages() throws IOException {
		if (provider == null)
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
		}
	}
	
	public void removeExample(ImageData image) {
		if (image != null) {
			examples.remove(image);
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
	
	
	public void reorderPage(int page) {
		if (provider == null || pages[page-1] == null)
			return;
		for (int i = 0; i < pages[page-1].length; i++) {
			if (pages[page-1][i] != null) {
				aggregator.clearValues();
				measure.setTarget(pages[page-1][i].getRgbImage());
				Iterator it = examples.iterator();
				while (it.hasNext()) {
					measure.setQuery(((ImageData)it.next()).getRgbImage());
					aggregator.addValue(measure.getSimilarity());
				}
				pages[page-1][i].setSimilarity(aggregator.getAggregatedValue());
			}
		}
		Arrays.sort(pages[page-1], comparator);
	}
	
	public void mergeReorderedPages() {
		if (provider == null)
			return;
		
		ImageData[][] arrays = new ImageData[getPageCount()][];
		for (int i = 0; i < getPageCount(); i++)
			arrays[i] = pages[i];
		List l = new ArraysBackedList(arrays, getPageSize());
		Collections.sort(l, comparator);
	}
	
	public void reorderImages() {
		for (int i = 1; i <= getPageCount(); i++)
			reorderPage(i);
		mergeReorderedPages();
	}
}
