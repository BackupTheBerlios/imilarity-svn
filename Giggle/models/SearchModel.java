/*
 * Created on 25-sep-2005
 */
package models;


import java.io.IOException;
import java.util.Observable;


import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.providors.Providor;
import de.berlios.imilarity.providors.YahooProvidor;



/**
 * @author Klaas Bosteels
 */
public class SearchModel extends Observable {

	private Providor providor;
	
	public SearchModel(ExamplesModel examplesModel) {
		super();
		if (examplesModel == null)
			throw new NullPointerException("examplesModel == null");
	}
	
	public void setKeywords(String keywords) {
		providor = new YahooProvidor(keywords);
		setChanged();
		notifyObservers();
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
		return providor.getPage(page);
	}
}
