/*
 * Created on 29-sep-2005
 */
package image;

/**
 * @author Klaas Bosteels
 */
public class AggregatedColorImage implements ColorImage {

	private ScalableColorImage[] images;
	
	/**
	 * @see image.ColorImage#getColorValues(int)
	 */
	public int[] getColorValues(int pixelNr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see image.Image#getWidth()
	 */
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see image.Image#getHeight()
	 */
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
