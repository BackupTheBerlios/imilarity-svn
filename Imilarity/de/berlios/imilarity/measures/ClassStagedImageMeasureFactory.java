/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;


public class ClassStagedImageMeasureFactory implements StagedImageMeasureFactory {

	private Class c;
	
	public ClassStagedImageMeasureFactory(Class c) {
		if (c == null)
			throw new NullPointerException("c == null");
		this.c = c;
	}
	
	public StagedImageMeasure createMeasure() {
		try {
			return (StagedImageMeasure) c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
