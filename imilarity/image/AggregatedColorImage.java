/*
 * Created on 29-sep-2005
 */
package image;

import aggregators.Aggregator;

/**
 * @author Klaas Bosteels
 */
public class AggregatedColorImage implements ColorImage {

	private ColorImage[] scaledImages;
	private Aggregator aggregator;
	
	private static final int WIDTH = 100, HEIGHT = 100;
	
	public AggregatedColorImage(ScalableColorImage[] images, Aggregator aggregator) {
		if (images == null)
			throw new NullPointerException("images == null");
		if (aggregator == null)
			throw new NullPointerException("aggregator == null");
		this.aggregator = aggregator;
		scaledImages = new ColorImage[images.length];
		for (int i = 0; i < images.length; i++)
			scaledImages[i] = images[i].getScaledInstance(WIDTH,HEIGHT);
	}
	
	
	/**
	 * @see image.ColorImage#getColorValues(int)
	 */
	public int[] getColorValues(int pixelNr) {
		int values[][] = new int[3][scaledImages.length];
		for (int i = 0; i < scaledImages.length; i++) {
			int colorValues[] = scaledImages[i].getColorValues(pixelNr);
			for (int j = 0; j < colorValues.length; j++)
				values[j][i] = colorValues[j];
		}
		int result[] = new int[3];
		for (int i = 0; i < result.length; i++)
			result[i] = aggregator.aggregatedValue(values[i]);
		return result;
	}

	/**
	 * @see image.Image#getWidth()
	 */
	public int getWidth() {
		return WIDTH;
	}

	/**
	 * @see image.Image#getHeight()
	 */
	public int getHeight() {
		return HEIGHT;
	}

}
