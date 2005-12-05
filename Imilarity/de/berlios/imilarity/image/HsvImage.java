package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Hsv;

public class HsvImage extends ColorSpaceImage {

	public HsvImage(Image image) {
		super(image, new Hsv());
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
	}

}
