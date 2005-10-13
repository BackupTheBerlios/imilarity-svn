package de.berlios.imilarity.image;

public class HueImageAdapter extends GrayscaleImageBase {

	private ColorImage image;

	public HueImageAdapter(ColorImage image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
	}
	
	public int getGrayscaleValue(int x, int y) {
		int[] rgb = image.getColorValues(x,y);
		int r = rgb[0], g = rgb[1], b = rgb[1];
		double delta = Math.acos((((r-g)+(r-b))/2) / Math.sqrt((r-g)*(r-g)+(r-b)*(g-b)));
		return (int) (((b <= g) ? delta : 360 - delta) / 360 * 255);
	}

	public GrayscaleImage getScaledInstance(int w, int h) {
		return new HueImageAdapter(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
