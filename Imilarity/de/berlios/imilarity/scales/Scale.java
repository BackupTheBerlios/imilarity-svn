package de.berlios.imilarity.scales;

import de.berlios.imilarity.image.Image;

public interface Scale {
	
	public void setImage(Image image);
	
	public double getWeight(int pixelNr);
	
	public String getDescription();

}
