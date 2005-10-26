package de.berlios.imilarity.image;

public class ParameterizedImage extends ImageBase {

	private Image image;
	private double[][] parameters;
	private int w, h;
	private double min, max;
	
	public ParameterizedImage(Image image) {
		if (image == null)
			throw new NullPointerException("image == null");
		this.image = image;
		if (image.getColorComponentsCount() != 3)
			throw new IllegalArgumentException("image must have 3 color components");
		w = image.getWidth();
		h = image.getHeight();
		parameters = new double[w][h];
		Color c = image.getColor(0,0);
		double r = c.getComponents()[0];
		double g = c.getComponents()[1];
		double b = c.getComponents()[2];
		min = max = (r*g*b) / ((r*r*r)+(g*g*g)+(b*b*b));
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				c = image.getColor(i,j);
				r = c.getComponents()[0];
				g = c.getComponents()[1];
				b = c.getComponents()[2];
				parameters[i][j] = (r*g*b) / ((r*r*r)+(g*g*g)+(b*b*b)); 
				if (parameters[i][j] < min)
					min = parameters[i][j];
				if (parameters[i][j] > max)
					max = parameters[i][j];
			}
		}
	}

	public int getColorComponentsCount() {
		return 1;
	}

	public Color getColor(int x, int y) {
		return new Color((max - parameters[x][y]) / (max - min));
	}

	public Image getScaledInstance(int w, int h) {
		return new ParameterizedImage(image.getScaledInstance(w,h));
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}
	
	
}
