package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.ColorImage;
import de.berlios.imilarity.image.HueImageAdapter;

public class HueColorMeasure extends ColorMeasureBase {

	private GrayscaleMeasure measure;
	
	public HueColorMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(ColorImage image) {
		super.setQuery(image);
		measure.setQuery(new HueImageAdapter(image));
	}
	
	public void setTarget(ColorImage image) {
		super.setTarget(image);
		measure.setTarget(new HueImageAdapter(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Hue using " + measure.getDescription();
	}

}
