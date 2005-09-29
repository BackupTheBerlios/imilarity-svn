/*
 * Created on 28-sep-2005
 */
package image;

/**
 * @author Klaas Bosteels
 */
public class GrayscaleImageAdapter implements ScalableGrayscaleImage {

	private ScalableColorImage colorImage;
	
	public GrayscaleImageAdapter(ScalableColorImage colorImage) {
		if (colorImage == null)
			throw new NullPointerException("colorImage == null");
		this.colorImage = colorImage;
	}
	
	
	/**
	 * @see image.ScalableGrayscaleImage#getGrayscaleValue(int)
	 */
	public int getGrayscaleValue(int pixelNr) {
		int[] rgb = colorImage.getColorValues(pixelNr);
		return (int) (rgb[0]*0.2125 + rgb[1]*0.7154 + rgb[2]*0.0721);
	}

	/**
	 * @see image.ScalableGrayscaleImage#getScaledInstance(int, int)
	 */
	public ScalableGrayscaleImage getScaledInstance(int w, int h) {
		return new GrayscaleImageAdapter(colorImage.getScaledInstance(w,h));
	}

	/**
	 * @see image.Image#getWidth()
	 */
	public int getWidth() {
		return colorImage.getWidth();
	}

	/**
	 * @see image.Image#getHeight()
	 */
	public int getHeight() {
		return colorImage.getHeight();
	}

}
