package de.berlios.imilarity.image.quantizers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.color.ColorSpace;
import de.berlios.imilarity.color.Hsv;
import de.berlios.imilarity.image.HsvImage;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;


public class SctQuantizer implements Quantizer {

	private Image image;
	
	private int gbc = 16; // grayscale bins count
	private int cbc = 240; // color bins count
	
	public void quantize(Image im) {
		if (!(im instanceof HsvImage))
			throw new IllegalArgumentException("im should be an instance of HsvImage");
		image = im;
	}

	public int getBinsCount() {
		return gbc + cbc;
	}

	public Color getBinColor(int i) {
		double[] comps;
		if (i < gbc)
			comps = new double[] {0, 0, i*1.0/(gbc-1)};
		else
			comps = new double[] {(i-gbc)*1.0/cbc + 1.0/(2.0*cbc), 1, 1};
		return new Color(comps);
	}
	
	public int getBin(int i) {
		double[] hsv = image.getColor(i).getComponents();
		if (hsv[1] <= (1 - (0.8*hsv[2])))
			return Math.min((int)(hsv[2]*gbc),gbc-1); 
				// uniform quantized value
		else
			return (gbc + Math.min((int)(hsv[0]*cbc),cbc-1)); 
				// uniform quantized hue
	}

	public String getDescription() {
		return "SCT";
	}

	
	
	// TESTPROGRAMMA
	
	public static void main(String[] args) throws IOException {
		ColorSpace cs = new Hsv();
		Image image = new HsvImage(
			(ImageData.loadFile("/home/klbostee/Thesis/Misc/hsv_constant_s.png")).getRgbImage());
		Quantizer quantizer = new SctQuantizer();
		quantizer.quantize(image);
		BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Color color = quantizer.getBinColor(quantizer.getBin(y*image.getWidth()+x));
				int[] rgb = cs.toRgb(color);
				bi.setRGB(x, y, (rgb[0] << 16) | (rgb[1] << 8) | rgb[2]);
			}
		}
		ImageIO.write(bi, "png", new File("/home/klbostee/Thesis/Misc/sct_constant_s.png"));
		System.out.println("done.");
	}
}
