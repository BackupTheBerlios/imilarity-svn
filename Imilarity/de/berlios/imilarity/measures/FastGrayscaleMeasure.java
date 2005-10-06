/*
 * Created on 20-sep-2005
 */
package de.berlios.imilarity.measures;


/**
 * @author Klaas Bosteels
 */
public interface FastGrayscaleMeasure extends GrayscaleMeasure {
	
	public void compare(int v1, int v2);
	public double combine();
	public void reset();

}
