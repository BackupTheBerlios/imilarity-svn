/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;


public class M20 extends AvarageGrayscaleMeasure {

	public double m(double x, double y) {
		if (x == y) return 1;
		else if (x < y) return x / y;
		else return y / x;
	}

	public String getDescription() {
		return "M20";
	}

}
