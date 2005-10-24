package de.berlios.imilarity.image.quantizers;

import de.berlios.imilarity.image.Image;

public interface ColorQuantizer {

	public void quantize(Image im);
	
	public int getColorCount();
	
	public int[] getColor(int i);
	
	public int[] getPixelColor(int i);
	
	public String getDescription();
}
