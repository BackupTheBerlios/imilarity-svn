/*
 * Created on 25-sep-2005
 */
package de.berlios.imilarity.providors;

import java.io.IOException;

import de.berlios.imilarity.image.ImageData;





/**
 * @author Klaas Bosteels
 */
public interface Providor {
	
	public int getPageSize();
	public int getPageCount();
	public ImageData[] getPage(int page) throws IOException;
	
	public String getDescription();
}
