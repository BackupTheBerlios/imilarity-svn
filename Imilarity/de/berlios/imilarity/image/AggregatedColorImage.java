/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.image;

import de.berlios.imilarity.aggregators.Aggregator;

/**
 * @author Klaas Bosteels
 */
public class AggregatedColorImage extends ColorImageBase {

	private ColorImage[] images, scaledImages;
	private Aggregator aggregator;
	
	private static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 100;
	
	public AggregatedColorImage(ColorImage[] images, Aggregator aggregator,
			int width, int height) {
		if (images == null)
			throw new NullPointerException("images == null");
		if (aggregator == null)
			throw new NullPointerException("aggregator == null");
		this.aggregator = aggregator;
		this.images = images;
		scaledImages = new ColorImage[images.length];
		for (int i = 0; i < images.length; i++)
			scaledImages[i] = images[i].getScaledInstance(width, height);
	}
	
	public AggregatedColorImage(ColorImage[] images, Aggregator aggregator) {
		this(images, aggregator, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	
	/**
	 * @see de.berlios.imilarity.image.ColorImage#getColorValues(int,int)
	 */
	public int[] getColorValues(int x, int y) {
		int values[][] = new int[3][scaledImages.length];
		for (int i = 0; i < scaledImages.length; i++) {
			int colorValues[] = scaledImages[i].getColorValues(x, y);
			for (int j = 0; j < colorValues.length; j++)
				values[j][i] = colorValues[j];
		}
		int result[] = new int[3];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < values[i].length; j++)
				aggregator.addValue(values[i][j]);
			result[i] = (int) aggregator.getAggregatedValue();
			aggregator.clearValues();
		}
		return result;
	}

	/**
	 * @see de.berlios.imilarity.image.Image#getWidth()
	 */
	public int getWidth() {
		return DEFAULT_WIDTH;
	}

	/**
	 * @see de.berlios.imilarity.image.Image#getHeight()
	 */
	public int getHeight() {
		return DEFAULT_HEIGHT;
	}


	public ColorImage getScaledInstance(int w, int h) {
		return new AggregatedColorImage(images, aggregator, w, h);
	}

}
