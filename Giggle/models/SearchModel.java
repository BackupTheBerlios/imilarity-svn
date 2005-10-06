/*
 * Created on 25-sep-2005
 */
package models;


import java.io.IOException;
import java.util.Observable;

import be.ugent.imilarity.Imilarity;
import be.ugent.imilarity.image.ImageData;
import be.ugent.imilarity.measures.ColorMeasure;
import be.ugent.imilarity.providors.Providor;



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
	
	public void setProvidor(Providor providor) {
		imilarity.setProvidor(providor);
		setChanged();
		notifyObservers();
	}
	
	public void setMeasure(ColorMeasure measure) {
		imilarity.setMeasure(measure);
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
