/*
 * Created on 25-sep-2005
 */
package providors;

import java.io.IOException;

import image.ImageData;


/**
 * @author Klaas Bosteels
 */
public interface Providor {
	
	public int getPageSize();
	public int getPageCount();
	public ImageData[] getPage(int page) throws IOException;
	
	public String getDescription();
}
