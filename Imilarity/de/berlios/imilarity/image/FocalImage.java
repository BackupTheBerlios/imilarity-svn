package de.berlios.imilarity.image;

public class FocalImage extends LabImage {

	private Image image;
	
	public static final Color[] COLORS = {
		new Color(0.0, 0.5, 0.5),													// black
		new Color(0.32033485893305097, 0.5742204289459502, 0.29164200916660354),	// blue
		new Color(0.8733904898896725, 0.40423259756177077, 0.5963696819039991),		// green
		new Color(0.5423856777952881, 0.5749319939245413, 0.7419635604203512),		// red
		new Color(0.560453645849854, 0.5249153739617732, 0.5499862119390915),		// brown
		new Color(0.8056536608960891, 0.5061110640984988, 0.7022800690844268),		// orange
		new Color(0.9718700296534624, 0.4710022111493074, 0.6932125816353195),		// yellow
		new Color(0.6120635895385, 0.5589394040639345, 0.40673350251395035),		// purple
		new Color(0.8953058330600423, 0.4924456389121535, 0.47933271439168756),		// grey
		new Color(0.9216954542254809, 0.5036809970983391, 0.4934516431636776),		// pink
		new Color(1.0, 0.4916961902536898, 0.4772823663486011)						// white
	};
	
	public FocalImage(Image image) {
		super(image);
		this.image = image;
	}
	
	public int getColorComponentsCount() {
		return 1;
	}

	public Color getColor(int x, int y) {
		double d = 2.0;
		int index = 0;
		double[] lab = super.getColor(x,y).getComponents();
		for (int i = 0; i < COLORS.length; i++) {
			double cd = distance(lab, COLORS[i].getComponents());
			if (cd < d) {
				d = cd;
				index = i;
			}
		}
		return new Color(index * 1.0 / (COLORS.length-1));
	}

	private static double distance(double[] c1, double[] c2) {
		double sum = 0.0;
		for (int i = 0; i < 3; i++) {
			double v = c1[i] - c2[i];
			sum += v*v;
		}
		return Math.sqrt(sum); // / Math.sqrt(3);
	}
	
	public Image getScaledInstance(int w, int h) {
		return new FocalImage(image.getScaledInstance(w, h));
	}

}
