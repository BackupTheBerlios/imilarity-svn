/*
 * Created on 23-sep-2005
 */
package measures;

import image.ScalableGrayscaleImage;


/**
 * @author Klaas Bosteels
 */
public class ScalingGrayscaleMeasure extends GrayscaleMeasureBase {

	private GrayscaleMeasure measure;
	
	public ScalingGrayscaleMeasure(GrayscaleMeasure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public double similarity(ScalableGrayscaleImage gi1, ScalableGrayscaleImage gi2) {
		ScalableGrayscaleImage sGi1 = gi1.getScaledInstance(100,100);
		ScalableGrayscaleImage sGi2 = gi2.getScaledInstance(100,100);
		return measure.similarity(sGi1,sGi2);
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
