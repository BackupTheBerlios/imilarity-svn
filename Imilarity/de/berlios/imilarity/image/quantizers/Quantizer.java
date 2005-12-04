package de.berlios.imilarity.image.quantizers;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.Image;

public interface Quantizer {

	public void quantize(Image im);
	
	public int getBinsCount();
	
	public Color getBinColor(int binNr);
	
	public int getBin(int pixelNr);
	
	public String getDescription();
}
