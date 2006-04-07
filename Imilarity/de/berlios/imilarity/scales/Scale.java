package de.berlios.imilarity.scales;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.util.Describable;

public interface Scale extends Describable {
	
	public void setImage(Image image);
	
	public double getWeight(int pixelNr);
	
}
