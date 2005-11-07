/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.image;

public class PartOfImage extends ImageBase {

	private Image image;
	private int ox, oy, width, height;
	
	public PartOfImage(Image image, int ox, int oy, int w, int h) {
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
	
	
	public int getColorComponentsCount() {
		return image.getColorComponentsCount();
	}
	
	public Color getColor(int x, int y) {
		if (ox+x >= image.getWidth() || oy+y >= image.getHeight()) {
			double[] blackComponents = new double[image.getColorComponentsCount()];
			for (int i = 0; i < blackComponents.length; i++)
				blackComponents[i] = 0.0;
			return new Color(blackComponents); // zwart teruggeven als we buiten de grenzen gaan
		}
		return image.getColor(ox+x, oy+y);
	}

	public Image getScaledInstance(int w, int h) {
		return new PartOfImage(image, ox, oy, w, h);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
