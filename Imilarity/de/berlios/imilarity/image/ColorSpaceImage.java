package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.color.ColorSpace;

public class ColorSpaceImage extends ImageBase {

	private ColorSpace cs;
	private Image image;
	
	public ColorSpaceImage(Image image, ColorSpace cs) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
		if (cs == null)
			throw new NullPointerException("cs == null");
		this.cs = cs;
	}
	
	
	public int getColorComponentsCount() {
		return cs.getComponentsCount();
	}

	public Color getColor(int x, int y) {
		double[] comps = image.getColor(x,y).getComponents();
		return cs.fromRgb(new int[] {(int)(comps[0]*255),(int)(comps[1]*255),(int)(comps[2]*255)});
	}

	public Image getScaledInstance(int w, int h) {
		return new ColorSpaceImage(image.getScaledInstance(w,h),cs);
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
