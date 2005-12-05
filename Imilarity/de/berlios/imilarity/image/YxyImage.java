package de.berlios.imilarity.image;


import de.berlios.imilarity.color.Yxy;

public class YxyImage extends ColorSpaceImage {
	
	public YxyImage(Image image) {
		super(image, new Yxy());
	}
	
}
 