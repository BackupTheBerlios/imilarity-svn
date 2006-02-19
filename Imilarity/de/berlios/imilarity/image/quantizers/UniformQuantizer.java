package de.berlios.imilarity.image.quantizers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.color.ColorSpace;
import de.berlios.imilarity.color.Hsv;
import de.berlios.imilarity.color.I1i2i3;
import de.berlios.imilarity.color.Irb;
import de.berlios.imilarity.color.Lab;
import de.berlios.imilarity.color.Xyz;
import de.berlios.imilarity.color.Yxy;
import de.berlios.imilarity.image.HsvImage;
import de.berlios.imilarity.image.I1i2i3Image;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.IrbImage;
import de.berlios.imilarity.image.LabImage;
import de.berlios.imilarity.image.XyzImage;
import de.berlios.imilarity.image.YxyImage;

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
		ColorSpace hsv = new Hsv();
		ColorSpace i1i2i3 = new I1i2i3();
		ColorSpace irb = new Irb();
		ColorSpace xyz = new Xyz();
		ColorSpace yxy = new Yxy();
		ColorSpace lab = new Lab();
		Image flowers = (ImageData.loadFile("/home/klbostee/Thesis/Misc/flowers.png")).getRgbImage();
		Image autumn = (ImageData.loadFile("/home/klbostee/Thesis/Misc/autumn.png")).getRgbImage();
		Image constS = (ImageData.loadFile("/home/klbostee/Thesis/Misc/hsv_constant_s.png")).getRgbImage();
		Image constV = (ImageData.loadFile("/home/klbostee/Thesis/Misc/hsv_constant_v.png")).getRgbImage();
		
		doTest(hsv, new HsvImage(flowers), new int[] {16,4,4}, 
				new File("/home/klbostee/Thesis/Misc/uniform_hsv_flowers.png"));
		doTest(hsv, new HsvImage(autumn), new int[] {16,4,4}, 
				new File("/home/klbostee/Thesis/Misc/uniform_hsv_autumn.png"));
		doTest(hsv, new HsvImage(constS), new int[] {16,4,4}, 
				new File("/home/klbostee/Thesis/Misc/uniform_hsv_constant_s.png"));
		doTest(hsv, new HsvImage(constV), new int[] {16,4,4}, 
				new File("/home/klbostee/Thesis/Misc/uniform_hsv_constant_v.png"));
		
		doTest(i1i2i3, new I1i2i3Image(flowers), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_i1i2i3_flowers.png"));
		doTest(i1i2i3, new I1i2i3Image(autumn), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_i1i2i3_autumn.png"));
		doTest(i1i2i3, new I1i2i3Image(constS), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_i1i2i3_constant_s.png"));
		doTest(i1i2i3, new I1i2i3Image(constV), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_i1i2i3_constant_v.png"));
		
		doTest(irb, new IrbImage(flowers), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_irb_flowers.png"));
		doTest(irb, new IrbImage(autumn), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_irb_autumn.png"));
		doTest(irb, new IrbImage(constS), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_irb_constant_s.png"));
		doTest(irb, new IrbImage(constV), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_irb_constant_v.png"));
		
		doTest(xyz, new XyzImage(flowers), new int[] {8,4,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_xyz_flowers.png"));
		doTest(xyz, new XyzImage(autumn), new int[] {8,4,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_xyz_autumn.png"));
		doTest(xyz, new XyzImage(constS), new int[] {8,4,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_xyz_constant_s.png"));
		doTest(xyz, new XyzImage(constV), new int[] {8,4,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_xyz_constant_v.png"));
		
		doTest(yxy, new YxyImage(flowers), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_yxy_flowers.png"));
		doTest(yxy, new YxyImage(autumn), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_yxy_autumn.png"));
		doTest(yxy, new YxyImage(constS), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_yxy_constant_s.png"));
		doTest(yxy, new YxyImage(constV), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_yxy_constant_v.png"));
		
		doTest(lab, new LabImage(flowers), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_lab_flowers.png"));
		doTest(lab, new LabImage(autumn), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_lab_autumn.png"));
		doTest(lab, new LabImage(constS), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_lab_constant_s.png"));
		doTest(lab, new LabImage(constV), new int[] {4,8,8}, 
				new File("/home/klbostee/Thesis/Misc/uniform_lab_constant_v.png"));
	}
	
	public static void doTest(ColorSpace cs, Image image, int[] binsCounts, File file) throws IOException {
		Quantizer quantizer = new UniformQuantizer(binsCounts);
		quantizer.quantize(image);
		BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Color color = quantizer.getBinColor(quantizer.getBin(y*image.getWidth()+x));
				int[] rgb = cs.toRgb(color);
				bi.setRGB(x, y, (rgb[0] << 16) | (rgb[1] << 8) | rgb[2]);
			}
		}
		ImageIO.write(bi, "png", file);
		System.out.println("done.");
	}
}
