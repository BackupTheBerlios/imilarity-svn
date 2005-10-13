/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.image;

public class PartOfGrayscaleImage extends ColorImageBase {

	private ColorImage image;
	private int ox, oy, width, height;
	
	public PartOfGrayscaleImage(ColorImage image, int ox, int oy, int w, int h) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
		if (ox < 0 || ox > image.getWidth())
			throw new IllegalArgumentException("ox out of bounds");
		this.ox = ox;
		if (oy < 0 || oy > image.getHeight())
			throw new IllegalArgumentException("oy out of bounds");
		this.oy = oy;
		width = w;
		height = h;
	}
	
	
	public Color getColor(int x, int y) {
		if (ox+x >= image.getWidth() || oy+y >= image.getHeight())
			return new Grayscale0; // zwart teruggeven als we buiten de grenzen gaan
		return image.getGrayscaleValue(ox+x, oy+y);
	}

	public GrayscaleImage getScaledInstance(int w, int h) {
		return new PartOfGrayscaleImage(image, ox, oy, w, h);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
