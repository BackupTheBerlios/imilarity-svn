/*
 * Created on 28-sep-2005
 */
package image;

/**
 * @author Klaas Bosteels
 */
public class ColorImageAdapter implements ScalableColorImage {

	private ImageData imageData;
	
	public ColorImageAdapter(ImageData id) {
		if (id == null)
			throw new NullPointerException("id == null");
		imageData = id;
	}
	
	
	/**
	 * @see image.ScalableColorImage#getColorValues(int)
	 */
	public int[] getColorValues(int pixelNr) {
		return imageData.getRgb(pixelNr);
	}

	/**
	 * @see image.ScalableColorImage#getScaledInstance(int, int)
	 */
	public ScalableColorImage getScaledInstance(int w, int h) {
		return new ColorImageAdapter(imageData.getScaledInstance(w,h));
	}

	/**
	 * @see image.Image#getWidth()
	 */
	public int getWidth() {
		return imageData.getWidth();
	}

	/**
	 * @see image.Image#getHeight()
	 */
	public int getHeight() {
		return imageData.getHeight();
	}

}
