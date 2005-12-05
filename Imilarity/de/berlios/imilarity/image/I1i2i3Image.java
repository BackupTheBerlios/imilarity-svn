package de.berlios.imilarity.image;


import de.berlios.imilarity.color.I1i2i3;

public class I1i2i3Image extends ColorSpaceImage {

	public I1i2i3Image(Image image) {
		super(image, new I1i2i3());
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
	}

}
