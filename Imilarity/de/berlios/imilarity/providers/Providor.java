/*
 * Created on 25-sep-2005
 */
package de.berlios.imilarity.providers;

import java.io.IOException;

import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.util.Describable;


/**
 * @author Klaas Bosteels
 */
public interface Providor extends Describable {
	
	public int getPageSize();
	public int getPageCount();
	public ImageData[] getPage(int page) throws IOException;
	
}
