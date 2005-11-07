package de.berlios.imilarity.image;

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
		return 3;
	}

	public Color getColor(int x, int y) {
		int[] comps = quantizer.getPixelColor(y*image.getWidth()+x);
		return new Color(comps[0], comps[1], comps[2]);
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

}
