/*
 * Created on 28-sep-2005
 */
package image;

/**
 * @author Klaas Bosteels
 */
public interface ScalableColorImage extends ColorImage {
	
	public ScalableColorImage getScaledInstance(int w, int h);
	
}
