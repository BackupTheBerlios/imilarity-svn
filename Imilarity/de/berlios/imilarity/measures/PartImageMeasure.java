/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.PartOfGrayscaleImage;

public class PartImageMeasure extends StagedImageMeasureBase {

	private StagedImageMeasureFactory factory;
	private int width, height; // de grootte van de ingestelde image
	private int cols = 0, rows = 0; // geven het aantal deel-images aan
	private StagedImageMeasure[] measures;
	
	private static final int PART_W = 10, PART_H = 10;
	
	public PartImageMeasure(StagedImageMeasureFactory factory) {
		if (factory == null)
			throw new NullPointerException("factory == null");
		this.factory = factory;
	}
	
	public void setQuery(Image query) {
		super.setQuery(query);
		// breedte en hoogte worden naar boven afgerond:
		width = ((query.getWidth() + PART_W - 1) / PART_W) * PART_W;
		height = ((query.getHeight() + PART_H - 1) / PART_H) * PART_H;
		cols = width / PART_W;
		rows = height / PART_H;
		measures = new StagedImageMeasure[cols * rows];
		for (int i = 0; i < measures.length; i++)
			measures[i] = factory.createMeasure();
	}
	
	public void setTarget(Image target) {
		if (!sameResolution(getQuery(), target))
			throw new IllegalArgumentException("query and target must have same resolution");
		super.setTarget(target);
	}
	
	public void compare(int pixelNr) {
		int partX = (pixelNr % width) % PART_W; // = x % PART_W
		int partY = (pixelNr / width) % PART_H; // = y % PART_H
		int c = (pixelNr % width) / PART_W; // = x / PART_W
		int r = (pixelNr / width) / PART_H; // = y / PART_H
		int index = ((width/PART_W)*r)+c;
		if (partX == 0 && partY == 0) {
			measures[index].setQuery
				(new PartOfGrayscaleImage(getQuery(),c*PART_W,r*PART_H,PART_W,PART_H));
			measures[index].setTarget
				(new PartOfGrayscaleImage(getTarget(),c*PART_W,r*PART_H,PART_W,PART_H));
		}
		measures[index].compare(partY * PART_W + partX);
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
	}

	
	public String getDescription() {
		return "Partitioning " + factory.createMeasure().getDescription();
	}
	
}
