/*
 * Created on 26-sep-2005
 */
package de.berlios.imilarity.providors;

import java.io.IOException;

import de.berlios.imilarity.image.ImageData;



/**
 * @author Klaas Bosteels
 */
public abstract class ProvidorBase implements Providor {

	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	
	/**
	 * Deze methode moet opgeroepen worden zodra <code>getPageSize()</code> en
	 * <code>getPageCount()</code> de juiste waarden teruggeven.
	 */
	protected void init() {
		pages = new ImageData[getPageCount()][getPageSize()];
		for (int i = 0; i < pages.length; i++)
			pages[i] = null;
		pageLoaded = new boolean[getPageCount()];
	}
	
	
	public String toString() {
		return getDescription();
	}
	
	public ImageData[] getPage(int page) throws IOException {
		if (page < 1 || page > getPageCount())
			throw new IOException("wrong page number");
		if (!pageLoaded[page-1]) {
			pageLoaded[page-1] = true;
			pages[page-1] = loadPage(page);
		}
		// Als deze methode in twee aparte draden uitgevoerd wordt dan kan het 
		// voorkomen dat 'pages[x]' nog gelijk is aan 'null', terwijl de inhoud
		// van 'pages[x]' wel al berekend wordt. In dat geval moet er gewacht worden...
		else while (pages[page-1] == null); 
		return pages[page-1];
	}
	
	public abstract ImageData[] loadPage(int page) throws IOException;
}
