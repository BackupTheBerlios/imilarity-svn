/*
 * Created on 23-sep-2005
 */
package measures;

import image.GrayscaleImage;


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
	
	public double similarity(GrayscaleImage gi1, GrayscaleImage gi2) {
		GrayscaleImage sGi1 = gi1.getScaledInstance(100,100);
		GrayscaleImage sGi2 = gi2.getScaledInstance(100,100);
		return measure.similarity(sGi1,sGi2);
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
