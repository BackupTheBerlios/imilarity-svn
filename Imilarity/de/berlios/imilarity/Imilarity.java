/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.image.AggregatedColorImage;
import de.berlios.imilarity.image.ColorImage;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.ScalableColorImage;
import de.berlios.imilarity.measures.ColorMeasure;
import de.berlios.imilarity.providors.Providor;
import de.berlios.imilarity.util.ArraysBackedList;

/**
 * @author Klaas Bosteels
 */
public class Imilarity {

	
	private Providor providor;
	private Aggregator aggregator;
	private ColorMeasure measure;
	
	private Collection examples = new HashSet(); 
	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	
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
	}
	
	
	
	public void loadImagesPage(int page) throws IOException {
		getImagesPage(page);
	}
	
	public void loadImages() throws IOException {
		getImages();
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
	
	public ImageData[] getImagesPage(int page) throws IOException {
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
		int ps = getPageSize(), pc = getPageCount();
		ImageData[] images = new ImageData[ps * pc];
		for (int i = 0; i < pc; i++) {
			ImageData[] pageImages = getImagesPage(i);
			for (int j = 0; j < pageImages.length; j++)
				images[i+j] = pageImages[j];
		}
		return images;
	}
	
	
	
	public void addExample(ImageData image) {
		examples.add(image);
	}
	
	public void removeExample(ImageData image) {
		examples.remove(image);
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
	
	
	public void reorderImages() {
		ColorImage aggregation = 
			new AggregatedColorImage((ScalableColorImage[])examples.toArray(), aggregator);
		ArraysBackedList l = new ArraysBackedList(getPageSize());
		for (int i = 0; i < getPageCount(); i++)
			l.addArray(pages[i]);
		measure.setImage(aggregation);
		Iterator it = l.iterator();
		while (it.hasNext()) {
			ImageData id = (ImageData) it.next();
			id.setSimilarity(measure.similarity(id.getColorImage()));
		}
		Collections.sort(l);
	}
}
