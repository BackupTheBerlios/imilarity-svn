package de.berlios.imilarity.image;

public interface ColorQuantizer {

	public void quantize(ColorImage im);
	
	public int getColorCount();
	
	public int[] getColor(int i);
	
	public int[] getPixelColor(int i);
	
}
