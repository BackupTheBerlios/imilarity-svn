package de.berlios.imilarity.image;

public class YxyImage extends XyzImage {

	public YxyImage(Image image) {
		super(image);
	}
	
	public Color getColor(int x, int y) {
		double[] xyz = super.getColor(x, y).getComponents();
		double sum = xyz[0] + xyz[1] + xyz[2];
		return new Color(xyz[0]/sum, xyz[1]/sum, xyz[1]);
	}
	
}
 