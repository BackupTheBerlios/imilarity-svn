/*
 * Created on 25-sep-2005
 */
package models;


import java.io.IOException;
import java.util.Observable;

import de.berlios.imilarity.Imilarity;
import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.measures.ImageMeasure;
import de.berlios.imilarity.providers.Provider;




/**
 * @author Klaas Bosteels
 */
public class SearchModel extends Observable {

	private Imilarity imilarity;
	
	public SearchModel(Imilarity imilarity) {
		super();
		if (imilarity == null)
			throw new NullPointerException("imilarity == null");
		this.imilarity = imilarity;		
	}
	
	public void setProvider(Provider provider) {
		imilarity.setProvider(provider);
		setChanged();
		notifyObservers();
	}
	
	public void setMeasure(ImageMeasure measure) {
		imilarity.setMeasure(measure);
	}
	
	public void setAggregator(Aggregator aggregator) {
		imilarity.setAggregator(aggregator);
	}
	
	
	public boolean isPageLoaded(int page) {
		return imilarity.isPageLoaded(page);
	}
	
	public boolean areImagesLoaded() {
		return imilarity.areImagesLoaded();
	}
	
	public void stopLoading() {
		imilarity.stopLoading();
	}
	
	public int getPageSize() {
		return imilarity.getPageSize();
	}
	
	public int getPageCount() {
		return imilarity.getPageCount();
	}
	
	public ImageData[] getPage(int page) throws IOException {
		return imilarity.getPage(page);
	}
	
	
	public void reorderPage(int page) {
		imilarity.reorderPage(page);
	}
	
	public void mergeReorderedPages() {
		imilarity.mergeReorderedPages();
		setChanged();
		notifyObservers();
	}
}
