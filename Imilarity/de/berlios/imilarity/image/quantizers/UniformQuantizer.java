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

public class UniformQuantizer implements Quantizer {

	private int[] binsCounts;
	private Image image;
	
	public UniformQuantizer(int[] binsCounts) {
		if (binsCounts == null)
			throw new NullPointerException("binscounts == null");
		this.binsCounts = binsCounts;
	}
	
	public void quantize(Image im) {
		image = im;
	}

	public int getBinsCount() {
		int count = 1;
		for (int i = 0; i < binsCounts.length; i++)
			count *= binsCounts[i];
		return count;
	}

	public Color getBinColor(int binNr) {
		double[] comps = new double[binsCounts.length];
		
		int l = binsCounts[0];
		for (int j = 1; j < binsCounts.length; j++)
			l *= binsCounts[j];
		
		int tmp = binNr;
		for (int j = binsCounts.length-1; j >= 0; j--) {
			l /= binsCounts[j];
			//double top = ((tmp/l)+1) * 1.0 / binsCounts[j];
			double bottom = (tmp/l) * 1.0 / binsCounts[j];
			//comps[j] = (bottom + top)/2;
			comps[j] = bottom + (1.0/(2*binsCounts[j]));
			tmp %= l;
		}
		
		return new Color(comps);
	}

	public int getBin(int pixelNr) {
		double[] comps = image.getColor(pixelNr).getComponents();
		int bin = Math.min((int)(comps[0]*binsCounts[0]),binsCounts[0]-1);
		int factor = 1;
		for (int j = 1; j < comps.length; j++) {
			factor *= binsCounts[j-1];
			bin += factor * Math.min((int)(comps[j]*binsCounts[j]),binsCounts[j]-1);
		}
		return bin;
	}

	public String getDescription() {
		return "Uniform";
	}

	
	
	// TESTPROGRAMMA
	
	public static void main(String[] args) throws IOException {
		ColorSpace cs = new Hsv();
		Image image = new HsvImage(
			(ImageData.loadFile("/home/klbostee/Thesis/Misc/hsv_constant_v.png")).getRgbImage());
		Quantizer quantizer = new UniformQuantizer(new int[] {16,4,4});
		quantizer.quantize(image);
		BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Color color = quantizer.getBinColor(quantizer.getBin(y*image.getWidth()+x));
				int[] rgb = cs.toRgb(color);
				bi.setRGB(x, y, (rgb[0] << 16) | (rgb[1] << 8) | rgb[2]);
			}
		}
		ImageIO.write(bi, "png", new File("/home/klbostee/Thesis/Misc/uniform_constant_v.png"));
		System.out.println("done.");
	}
}
