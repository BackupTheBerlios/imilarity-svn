/*
 * Created on 23-sep-2005
 */
package measures;

import image.ImageData;

/**
 * @author Klaas Bosteels
 */
public class ScalingMeasure extends MeasureBase {

	private Measure measure;
	
	public ScalingMeasure(Measure measure) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
	}
	
	public double similarity(ImageData id1, ImageData id2) {
		ImageData sId1 = id1.getScaledInstance(100,100);
		ImageData sId2 = id2.getScaledInstance(100,100);
		return measure.similarity(sId1,sId2);
	}
	
	public String getDescription() {
		return "Scaling " + measure.getDescription();
	}
}
