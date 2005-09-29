/*
 * Created on 25-sep-2005
 */
package models;


import java.io.IOException;
import java.util.Observable;

import de.berlios.imilarity.Imilarity;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.providors.YahooProvidor;


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
	
	public void setKeywords(String keywords) {
		imilarity.setProvidor(new YahooProvidor(keywords));
		setChanged();
		notifyObservers();
	}
	
	
	public int getPageSize() {
		return imilarity.getPageSize();
	}
	
	public int getPageCount() {
		return imilarity.getPageCount();
	}
	
	public ImageData[] getPage(int page) throws IOException {
		return imilarity.getImagesPage(page);
	}
}
