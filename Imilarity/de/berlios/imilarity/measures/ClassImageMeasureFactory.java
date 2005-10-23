/*
 * Created on 5-okt-2005
 */
package de.berlios.imilarity.measures;


public class ClassImageMeasureFactory implements ImageMeasureFactory {

	private Class c;
	
	public ClassImageMeasureFactory(Class c) {
		if (c == null)
			throw new NullPointerException("c == null");
		this.c = c;
	}
	
	public ImageMeasure createMeasure() {
		try {
			return (ImageMeasure) c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
