package de.berlios.imilarity.image;


import de.berlios.imilarity.image.quantizers.SctQuantizer;

public class SctImage extends QuantizedImage {
	
	public SctImage(Image image, int gbc, int cbc) {
		super(image, new SctQuantizer());
	}

}
