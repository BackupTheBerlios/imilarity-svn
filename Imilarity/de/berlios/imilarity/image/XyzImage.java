package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Xyz;

public class XyzImage extends ColorSpaceImage {

	public XyzImage(Image image) {
		super(image, new Xyz());
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
	}

}
