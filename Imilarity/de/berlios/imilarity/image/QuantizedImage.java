package de.berlios.imilarity.image;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.quantizers.Quantizer;

public class QuantizedImage extends ImageBase {

	private Image image;
	private Quantizer quantizer;
	
	public QuantizedImage(Image image, Quantizer quantizer) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.quantizer = quantizer;
		
		quantizer.quantize(image);
	}
	
	public int getColorComponentsCount() {
		return 1;
	}

	public Color getColor(int x, int y) {
		//return quantizer.getBinColor(quantizer.getBin(y*image.getWidth()+x));
		return new Color(quantizer.getBin(y*image.getWidth()+x)*1.0/quantizer.getBinsCount());
	}

	public Image getScaledInstance(int w, int h) {
		return new QuantizedImage(image.getScaledInstance(w,h), quantizer);
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}
	
	
	public Quantizer getQuantizer() {
		return quantizer;
	}

}
