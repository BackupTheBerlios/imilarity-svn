package de.berlios.imilarity.color;

public class Yxy extends Xyz {

	public Color fromRgb(int[] rgb) {
		double[] xyz = super.fromRgb(rgb).getComponents();
		double sum = xyz[0] + xyz[1] + xyz[2];
		return new Color(xyz[1], xyz[0]/sum, xyz[1]/sum);
	}
	
	public int[] toRgb(Color color) {
		double[] comps = color.getComponents();
		double x = comps[1] * comps[0] / comps[2];
		double y = comps[0];
		double z = - comps[0] * (comps[1]-1+comps[2]) / comps[2];
		return super.toRgb(new Color(x,y,z));
	}
}
