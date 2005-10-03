/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;


public class M4 extends AvarageGrayscaleMeasure {

	public double m(double x, double y) {
		return 1 - 
			(((x-y)*Math.log((1+x)/(1+y))+(y-x)*Math.log((2-x)/(2-y))) / (2*Math.log(2)));
	}

	public String getDescription() {
		return "M4";
	}

}
