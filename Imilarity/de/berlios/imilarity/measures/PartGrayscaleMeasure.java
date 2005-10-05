/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;
import de.berlios.imilarity.image.PartOfGrayscaleImage;

public class PartGrayscaleMeasure extends GrayscaleMeasureBase {

	private GrayscaleMeasure measure;
	private GrayscaleImage[] origImages;
	
	public PartGrayscaleMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setImage(GrayscaleImage image) {
		super.setImage(image);
		origImages = partition(image);
	}
	
	public double similarity(GrayscaleImage image) {
		GrayscaleImage orig = getImage();
		if (image == null || !sameResolution(orig,image))
			return 0.0;
		double sum = 0.0;
		GrayscaleImage[] images = partition(image);
		for (int i = 0; i < origImages.length; i++) {
			measure.setImage(origImages[i]);
			sum += measure.similarity(images[i]);
		}
		return sum / origImages.length;
	}
	
	public String getDescription() {
		return "Partitioning " + measure.getDescription();
	}
	
	
	private static final int PART_W = 10, PART_H = 10;
	
	private static GrayscaleImage[] partition(GrayscaleImage image) {
		int cols = image.getWidth() / PART_W;
		int rows = image.getHeight() / PART_H;
		GrayscaleImage[] result = new GrayscaleImage[cols * rows];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				result[(10*r)+c] = 
					new PartOfGrayscaleImage(image, c*PART_W, r*PART_H, PART_W, PART_H);
			}
		}
		return result;
	}
}
