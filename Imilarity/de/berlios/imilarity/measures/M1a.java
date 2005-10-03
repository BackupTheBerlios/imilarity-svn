/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;


public class M1a extends AvarageGrayscaleMeasure {

	public double m(double x, double y) {
		return 1 - Math.abs(x - y);
	}

	public String getDescription() {
		return "M1a";
	}

}
