/*
 * Created on 28-sep-2005
 */
package image;

/**
 * @author Klaas Bosteels
 */
public interface GrayscaleImage extends Image {

	public int getGrayscaleValue(int pixelNr);

	public GrayscaleImage getScaledInstance(int w, int h);
	
}
