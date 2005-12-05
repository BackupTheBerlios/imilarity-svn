package de.berlios.imilarity.color;


public interface ColorSpace {
	
	// public Color fromRgb(Color color)  ?
	public Color fromRgb(int[] rgb);
	public int[] toRgb(Color color);
	
	public int getComponentsCount();
	
}
