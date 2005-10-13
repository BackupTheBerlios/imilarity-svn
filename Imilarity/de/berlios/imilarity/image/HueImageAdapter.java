package de.berlios.imilarity.image;

public class HueImageAdapter extends ColorImageBase {

	private ColorImage image;

	public HueImageAdapter(ColorImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}
	
	public Color getColor(int x, int y) {
		double[] rgb = image.getColor(x,y).getComponents();
		double r = rgb[0], g = rgb[1], b = rgb[1];
		double delta = Math.acos((((r-g)+(r-b))/2) / Math.sqrt((r-g)*(r-g)+(r-b)*(g-b)));
		return new Color(((b <= g) ? delta : 360 - delta) / 360 * 255);
	}

	public ColorImage getScaledInstance(int w, int h) {
		return new HueImageAdapter(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
