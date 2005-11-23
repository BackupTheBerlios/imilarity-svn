/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ComponentImage;


public class ComponentsImageMeasure extends ImageMeasureBase {
	
	private ImageMeasure measure;
	private Image[] compImages;
	
	private int[] indices;
	
	public ComponentsImageMeasure(ImageMeasure measure, int[] indices) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		if (indices == null)
			throw new NullPointerException("indices == null");
		this.indices = indices;
	}
	
	public ComponentsImageMeasure(ImageMeasure measure) {
		this(measure, new int[] {0,1,2});
	}
	
	public void setQuery(Image image) {
		super.setQuery(image);
		compImages = new Image[image.getColorComponentsCount()];
		for (int i = 0; i < image.getColorComponentsCount(); i++)
			compImages[i] = new ComponentImage(image, i);
	}
	
	public double getSimilarity() {
		double sum = 0.0;
		for (int i = 0; i < indices.length; i++) {
			measure.setQuery(compImages[indices[i]]);
			measure.setTarget(new ComponentImage(getTarget(), i));
			sum += measure.getSimilarity();
		}
		return sum / indices.length;
	}

	public String getDescription() {
		return "Components " + measure.getDescription();
	}

}
