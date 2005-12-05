package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Lab;

public class LabImage extends ColorSpaceImage {

	public LabImage(Image image) {
		super(image, new Lab());
	}

}
