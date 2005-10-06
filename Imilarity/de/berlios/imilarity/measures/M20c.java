/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;


public class M20c extends M20 {

	public double m(double x, double y) {
		return super.m(1 - y, 1 - x);
	}

	public String getDescription() {
		return "M20c";
	}

}
