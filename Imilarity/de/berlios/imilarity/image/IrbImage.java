package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Irb;

public class IrbImage extends ColorSpaceImage {

	public IrbImage(Image image) {
		super(image, new Irb());
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
	}
	
}
