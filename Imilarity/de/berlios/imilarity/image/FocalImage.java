package de.berlios.imilarity.image;

import de.berlios.imilarity.image.quantizers.FocalQuantizer;

public class FocalImage extends QuantizedImage {
	
	public FocalImage(Image image) {
		super(image, new FocalQuantizer());
	}

}
