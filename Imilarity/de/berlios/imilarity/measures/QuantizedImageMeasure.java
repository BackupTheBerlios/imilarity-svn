package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.QuantizedImage;
import de.berlios.imilarity.image.quantizers.Quantizer;

public class QuantizedImageMeasure extends ImageMeasureBase {

	private ImageMeasure measure;
	private Quantizer quantizer;
	
	public QuantizedImageMeasure(ImageMeasure measure, Quantizer quantizer) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.quantizer = quantizer;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new QuantizedImage(image, quantizer));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new QuantizedImage(image, quantizer));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Quantized with " + quantizer.getDescription() + " using " 
			+ measure.getDescription();
	}

}
