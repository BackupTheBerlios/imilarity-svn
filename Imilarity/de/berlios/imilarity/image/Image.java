/*
 * Created on 28-sep-2005
 */
package de.berlios.imilarity.image;

/**
 * @author Klaas Bosteels
 */
public interface Image {
	
	public int getColorComponentsCount();
	public Color getColor(int x, int y);
	public Color getColor(int pixelNr);
	
	public Image getScaledInstance(int w, int h);
	
	public int getWidth();
	public int getHeight();
	
}
