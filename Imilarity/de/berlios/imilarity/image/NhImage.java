package de.berlios.imilarity.image;

public class NhImage extends HsvImage {

	private Image image;
	
	private int grayscaleBinsCount;
	private int colorBinsCount;
	
	
	public NhImage(Image image, int gbc, int cbc) {
		super(image);
		this.image = image;
		this.grayscaleBinsCount = gbc;
		this.colorBinsCount = cbc;
	}
	
	public NhImage(Image image) {
		this(image, 26, 230);
	}
	
	public int getColorComponentsCount() {
		return 1;
	}

	public Color getColor(int x, int y) {
		double[] hsv = super.getColor(x,y).getComponents();
		//if (hsv[1] < 0.15) {
		//System.out.println("s: " + hsv[1] + " tr: " + (1 - (0.9*hsv[2])));
		if (hsv[1] <= (1 - (0.8*hsv[2]))) {
//			if (hsv[2] < 0.2)
//				return new Color(0.0);		// black
//			else if (hsv[2] > 0.9)				
//				return new Color(2.0/(hueBinsCount + 2));	// white
//			else
//				return new Color(1.0/(hueBinsCount + 2));	// grey
			//double v = Math.sqrt(hsv[2]);
			return new Color(Math.min((int)(hsv[2]*grayscaleBinsCount),grayscaleBinsCount-1)
					* 1.0 / (colorBinsCount+grayscaleBinsCount-1)); // uniform quantized value
		} else
			return new Color((grayscaleBinsCount + Math.min((int)(hsv[0]*colorBinsCount),colorBinsCount-1))
					* 1.0 / (colorBinsCount+grayscaleBinsCount-1)); // uniform quantized hue
	}
	
	public Image getScaledInstance(int w, int h) {
		return new FocalImage(image.getScaledInstance(w, h));
	}
	
	
	public int getGrayscaleBinsCount() {
		return grayscaleBinsCount;
	}
	
	public int getColorBinsCount() {
		return colorBinsCount;
	}

}
