/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;


public class M2 extends MinimumGrayscaleMeasure {

	public double m(double x, double y) {
		return 1 - Math.abs(x - y);
	}
	
	public String getDescription() {
		return "M2";
	}

}
