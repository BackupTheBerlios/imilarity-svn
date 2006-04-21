package de.berlios.imilarity.scales;

import de.berlios.imilarity.image.Image;

public class CentrumScale implements Scale {

	private Image image;
	private int w, h;
	private double alpha, beta, gamma;
	
	public void setImage(Image image) {
		this.image = image;
		w = image.getWidth();
		h = image.getHeight();
		//gamma = Math.sqrt(w*w/4 + h*h/4); 
		gamma = Math.min(w/2, h/2);
		alpha = 0.6*gamma;
		beta = (gamma+alpha)/2.0;
	}

	public double getWeight(int pixelNr) {
		int x = pixelNr % image.getWidth();
		int y = pixelNr / image.getWidth();

		int v1 = w/2 - x, v2 = h/2 - y;
		double d = Math.sqrt(v1*v1+v2*v2);
		double value;
		if (d < alpha) 				// ]-infinity, alpha[
			value = 1;
		else if (d <= beta) { 		// [alpha, beta]
			value = (d - alpha)/(gamma - alpha);
			value = 1 - 2*value*value;
		} else if (d <= gamma) {	// [beta, gamma]
			value = (d - gamma)/(gamma - alpha);
			value = 2*value*value;
		} else value = 0;			// ]gamma, +infinity[
		return value;
	}
	
	public String getDescription() {
		return "Spatial scale";
	}

}
