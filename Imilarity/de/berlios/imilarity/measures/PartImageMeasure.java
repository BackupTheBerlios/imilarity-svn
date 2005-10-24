/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.PartOfGrayscaleImage;

public class PartImageMeasure extends ImageMeasureBase {

	private int width, height; // de grootte van de ingestelde image
	private int cols = 0, rows = 0; // geven het aantal deel-images aan
	private ImageMeasure measure;
	
	private static final int PART_W = 10, PART_H = 10;
	
	public PartImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image query) {
		super.setQuery(query);
		// breedte en hoogte worden naar boven afgerond:
		width = ((query.getWidth() + PART_W - 1) / PART_W) * PART_W;
		height = ((query.getHeight() + PART_H - 1) / PART_H) * PART_H;
		cols = width / PART_W;
		rows = height / PART_H;
	}
	
	public void setTarget(Image target) {
		if (!sameResolution(getQuery(), target))
			throw new IllegalArgumentException("query and target must have same resolution");
		super.setTarget(target);
	}
	
	public double getSimilarity() {
		int pc = width * height;
		double sum = 0.0;
		for (int i = 0; i < pc; i++) {
			int partX = (i % width) % PART_W; // = x % PART_W
			int partY = (i / width) % PART_H; // = y % PART_H
			int c = (i % width) / PART_W; // = x / PART_W
			int r = (i / width) / PART_H; // = y / PART_H
			if (partX == 0 && partY == 0) {
				measure.setQuery
					(new PartOfGrayscaleImage(getQuery(),c*PART_W,r*PART_H,PART_W,PART_H));
				measure.setTarget
					(new PartOfGrayscaleImage(getTarget(),c*PART_W,r*PART_H,PART_W,PART_H));
				sum += measure.getSimilarity();
			}
		}
		return sum / (cols*rows);
	}

	public String getDescription() {
		return "Partitioning " + measure.getDescription();
	}
	
}
