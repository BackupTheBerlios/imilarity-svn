package de.berlios.imilarity.scales;

import de.berlios.imilarity.image.Image;

public class DefaultScale implements Scale {

	public void setImage(Image image) {}
	
	public double getWeight(int pixelNr) {
		return 1;
	}

	public String getDescription() {
		return "Default scale";
	}

}
