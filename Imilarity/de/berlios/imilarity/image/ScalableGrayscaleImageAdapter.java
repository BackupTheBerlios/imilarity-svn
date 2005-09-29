/*
 * Created on 29-sep-2005
 */
package de.berlios.imilarity.image;

public class ScalableGrayscaleImageAdapter extends GrayscaleImageAdapter
		implements ScalableGrayscaleImage {

	private ScalableColorImage image;

	public ScalableGrayscaleImageAdapter(ScalableColorImage image) {
		super(image);
		this.image = image;
	}
	
	public ScalableGrayscaleImage getScaledInstance(int w, int h) {
		return new ScalableGrayscaleImageAdapter(image.getScaledInstance(w,h));
	}
}
