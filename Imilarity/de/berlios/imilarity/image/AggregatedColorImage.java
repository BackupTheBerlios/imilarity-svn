/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.image;

import de.berlios.imilarity.aggregators.Aggregator;

/**
 * @author Klaas Bosteels
 */
public class AggregatedColorImage extends ImageBase {

	private Image[] images, scaledImages;
	private Aggregator aggregator;
	
	private final int width, height, colorComponentsCount;
	
	public AggregatedColorImage(Image[] images, Aggregator aggregator,
			int width, int height, int colorComponentsCount) {
		if (images == null)
			throw new NullPointerException("images == null");
		if (aggregator == null)
			throw new NullPointerException("aggregator == null");
		this.aggregator = aggregator;
		this.images = images;
		this.width = width;
		this.height = height;
		this.colorComponentsCount = colorComponentsCount;
		scaledImages = new Image[images.length];
		for (int i = 0; i < images.length; i++)
			scaledImages[i] = images[i].getScaledInstance(width, height);
	}
	
	public AggregatedColorImage(Image[] images, Aggregator aggregator) {
		this(images, aggregator, images[0].getWidth(), images[0].getHeight(), 3);
	}
	
	
	public int getColorComponentsCount() {
		return colorComponentsCount;
	}
	
	/**
	 * @see de.berlios.imilarity.image.Image#getColor(int,int)
	 */
	public Color getColor(int x, int y) {
		double values[][] = new double[colorComponentsCount][scaledImages.length];
		for (int i = 0; i < scaledImages.length; i++) {
			Color color = scaledImages[i].getColor(x, y);
			double[] colorValues = color.getComponents();
			for (int j = 0; j < colorValues.length; j++)
				values[j][i] = colorValues[j];
		}
		double result[] = new double[colorComponentsCount];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < values[i].length; j++)
				aggregator.addValue(values[i][j]);
			result[i] = aggregator.getAggregatedValue();
			aggregator.clearValues();
		}
		return new Color(result);
	}

	/**
	 * @see de.berlios.imilarity.image.Image#getWidth()
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @see de.berlios.imilarity.image.Image#getHeight()
	 */
	public int getHeight() {
		return height;
	}


	public Image getScaledInstance(int w, int h) {
		return new AggregatedColorImage(images, aggregator, w, h, colorComponentsCount);
	}

}
