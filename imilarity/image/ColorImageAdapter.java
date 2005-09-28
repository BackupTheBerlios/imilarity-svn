/*
 * Created on 28-sep-2005
 */
package image;

/**
 * @author Klaas Bosteels
 */
public class ColorImageAdapter implements ColorImage {

	private ImageData imageData;
	
	public ColorImageAdapter(ImageData id) {
		if (id == null)
			throw new NullPointerException("id == null");
		imageData = id;
	}
	
	
	/**
	 * @see image.ColorImage#getColorValues(int)
	 */
	public int[] getColorValues(int pixelNr) {
		return imageData.getRgb(pixelNr);
	}

	/**
	 * @see image.ColorImage#getScaledInstance(int, int)
	 */
	public ColorImage getScaledInstance(int w, int h) {
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
