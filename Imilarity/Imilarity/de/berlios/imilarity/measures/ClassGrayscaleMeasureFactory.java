/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;


public class ClassGrayscaleMeasureFactory implements StagedGrayscaleMeasureFactory {

	private Class c;
	
	public ClassGrayscaleMeasureFactory(Class c) {
		if (c == null)
			throw new NullPointerException("c == null");
		this.c = c;
	}
	
	public StagedGrayscaleMeasure createMeasure() {
		try {
			return (StagedGrayscaleMeasure) c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
