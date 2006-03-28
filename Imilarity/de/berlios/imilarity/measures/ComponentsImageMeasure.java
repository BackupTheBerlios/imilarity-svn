/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.aggregators.Aggregator;
import de.berlios.imilarity.aggregators.ArithmeticMean;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ComponentImage;


public class ComponentsImageMeasure extends ImageMeasureBase {
	
	private ImageMeasure measure;
	private Image[] compImages;
	
	private int[] indices;
	
	private Aggregator aggregator;
	
	public ComponentsImageMeasure(ImageMeasure measure, int[] indices, Aggregator aggregator) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		if (indices == null)
			throw new NullPointerException("indices == null");
		this.indices = indices;
		if (aggregator == null)
			throw new NullPointerException("aggregator == null");
		this.aggregator = aggregator;
	}

	
	public ComponentsImageMeasure(ImageMeasure measure, Aggregator aggregator) {
		this(measure, new int[] {0,1,2}, aggregator);
	}
	
	public ComponentsImageMeasure(ImageMeasure measure) {
		this(measure, new ArithmeticMean());
	}
	
	
	public void setQuery(Image image) {
		super.setQuery(image);
		compImages = new Image[image.getColorComponentsCount()];
		for (int i = 0; i < image.getColorComponentsCount(); i++)
			compImages[i] = new ComponentImage(image, i);
	}
	
	public double getSimilarity() {
//		double sum = 0.0;
//		for (int i = 0; i < indices.length; i++) {
//			measure.setQuery(compImages[indices[i]]);
//			measure.setTarget(new ComponentImage(getTarget(), i));
//			sum += weights[i] * measure.getSimilarity();
//		}
//		return sum;
		aggregator.clearValues();
		for (int i = 0; i < indices.length; i++) {
			measure.setQuery(compImages[indices[i]]);
			measure.setTarget(new ComponentImage(getTarget(), i));
			aggregator.addValue(measure.getSimilarity());
		}
		return aggregator.getAggregatedValue();
	}

	public String getDescription() {
		return "Components with " + measure.getDescription()+" and "+aggregator.getDescription();
	}

}
