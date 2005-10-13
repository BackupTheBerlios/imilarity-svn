/*
 * Created on 3-okt-2005
 */
package de.berlios.imilarity.measures;

import de.berlios.imilarity.fuzzy.Membership;


public class M20c extends M20 {

	public double m(Membership x, Membership y) {
		return super.m(y.complement(), x.complement());
	}

	public String getDescription() {
		return "M20c";
	}

}
