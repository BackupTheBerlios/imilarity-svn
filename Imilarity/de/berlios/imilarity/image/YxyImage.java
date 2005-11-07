package de.berlios.imilarity.image;

public class YxyImage extends XyzImage {

	private Image image;
	
	public YxyImage(Image image) {
		super(image);
		this.image = image;
	}
	
	public Color getColor(int x, int y) {
		double[] xyz = super.getColor(x, y).getComponents();
		double sum = xyz[0] + xyz[1] + xyz[2];
		return new Color(xyz[0]/sum, xyz[1]/sum, xyz[1]);
	}
	
	public Image getScaledInstance(int w, int h) {
		return new YxyImage(image.getScaledInstance(w,h));
	}
	
}
 