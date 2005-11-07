package de.berlios.imilarity.image;

public class OpponentImage extends ImageBase {

	private Image image;

	public OpponentImage(Image image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
	}
	
	public int getColorComponentsCount() {
		return 3;
	}
	
	public Color getColor(int x, int y) {
		double[] rgb = image.getColor(x,y).getComponents();
		double r = rgb[0], g = rgb[1], b = rgb[2];
		
		//r = Math.pow(r, 1.0/3);
		//g = Math.pow(g, 1.0/3);
		//b = Math.pow(b, 1.0/3);
		
		//return new Color(new double[] { (1 - (r-g))/2, (1 - (((r+g)/2) - b))/2,
	//			(r+g+b)/3});
		return new Color((r + 2*(1-g) + b)/4, (r + (1-b))/2, (r+g+b)/3);
	}

	public Image getScaledInstance(int w, int h) {
		return new OpponentImage(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}
}
