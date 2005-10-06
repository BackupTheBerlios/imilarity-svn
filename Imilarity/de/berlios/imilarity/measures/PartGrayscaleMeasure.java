/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.GrayscaleImage;
import de.berlios.imilarity.image.PartOfGrayscaleImage;

public class PartGrayscaleMeasure extends FastGrayscaleMeasureBase {

	private FastGrayscaleMeasureFactory factory;
	private int width, height; // de grootte van de ingestelde image
	private int cols = 0, rows = 0; // geven het aantal deel-images aan
	private int pixelNr = 0; 
	private FastGrayscaleMeasure[] measures;
	
	private static final int PART_W = 10, PART_H = 10;
	
	public PartGrayscaleMeasure(FastGrayscaleMeasureFactory factory) {
		if (factory == null)
			throw new NullPointerException("factory == null");
		this.factory = factory;
	}
	
	public void setImage(GrayscaleImage image) {
		super.setImage(image);
		width = image.getWidth();
		height = image.getHeight();
		cols = width / PART_W;
		rows = height / PART_H;
		measures = new FastGrayscaleMeasure[cols * rows];
		for (int i = 0; i < measures.length; i++)
			measures[i] = factory.createMeasure();
	}
	
	public void compare(int v1, int v2) {
		int c = (pixelNr % width) / PART_W; // = x / PART_W
		int r = (pixelNr / width) / PART_H; // = y / PART_H
		int index = (10*r)+c;
		measures[index].setImage
			(new PartOfGrayscaleImage(getImage(),c*PART_W,r*PART_H,PART_W,PART_H));
		measures[index].compare(v1,v2);
		pixelNr++;
	}
	
	public double combine() {
		double sum = 0.0;
		for (int i = 0; i < measures.length; i++)
			sum += measures[i].combine();
		return sum / measures.length;
	}
	
	public void reset() {
		for (int i = 0; i < measures.length; i++)
			measures[i].reset();
		pixelNr = 0;
	}

	
	public String getDescription() {
		return "Partitioning " + factory.createMeasure().getDescription();
	}
	
}
