package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.LabImage;
import de.berlios.imilarity.image.Image;

public class LabImageMeasure extends ImageMeasureBase {
	
	private ImageMeasure measure;
	
	public LabImageMeasure(ImageMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		measure.setQuery(new LabImage(image));
	}
	
	public void setTarget(Image image) {
		super.setTarget(image);
		measure.setTarget(new LabImage(image));
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}

	public String getDescription() {
		return "Lab using " + measure.getDescription();
	}

}
