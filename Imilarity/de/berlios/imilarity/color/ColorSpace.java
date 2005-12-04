package de.berlios.imilarity.color;


public interface ColorSpace {
	
	public Color fromRgb(int[] rgb);
	public int[] toRgb(Color color);
	
}
