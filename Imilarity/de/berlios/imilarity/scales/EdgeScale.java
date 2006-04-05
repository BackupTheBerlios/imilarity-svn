package de.berlios.imilarity.scales;

import de.berlios.imilarity.image.Image;

public class EdgeScale implements Scale {

	private Image image;
	private int w, h;
	
	public void setImage(Image image) {
		this.image = image;
		w = image.getWidth();
		h = image.getHeight();
	}

	public double getWeight(int pixelNr) {
		int x = pixelNr % image.getWidth();
		int y = pixelNr / image.getWidth();

		double l = x > 0 ? gray(image.getColor(x-1,y).getComponents()) : 0; 
		double r = x < w-1 ? gray(image.getColor(x+1,y).getComponents()) : 0; 
		double a = y > 0 ? gray(image.getColor(x,y-1).getComponents()) : 0;
		double b = y < h-1 ? gray(image.getColor(x,y+1).getComponents()) : 0;
		double v1 = (r-l)/2.0, v2 = (b-a)/2.0;
		//double value = Math.pow(Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2),2);
		double value = 3*Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2);
		if (value > 1) value = 1;
		return value;
	}
	
	private static double gray(double[] comps) {
		//return 0.3*comps[0] + 0.59*comps[1] + 0.11*comps[2];
		return (comps[0]+comps[1]+comps[2])/3.0;
	}
	
	public String getDescription() {
		return "Edge scale";
	}
}
