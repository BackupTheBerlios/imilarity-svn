package de.berlios.imilarity.image.quantizers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.LabImage;

public class FocalQuantizer implements Quantizer {
	
	private static final int[][] COLORS = {
		new int[] {0, 0, 0}, 		// black	  0	  0   0
		new int[] {0, 0, 255}, 		// blue		  0   0 255
		new int[] {0 , 255, 0},		// green	  0 255   0
		new int[] {255, 0, 0}, 		// red		255   0   0
		new int[] {128, 42, 42},	// brown	128  42  42
		new int[] {255, 128, 0},	// orange	255 128   0
		new int[] {255, 255, 0}, 	// yellow	255 255   0
		new int[] {160, 32, 240}, 	// purple	160  32 240
		new int[] {192, 192, 192}, 	// gray		192 192 192
		new int[] {255, 192, 203}, 	// pink		255 192 203
		new int[] {255, 255, 255} 	// white	255 255 255
	};
	
	private static final double[][] LAB_COLORS = {
		new double[] {0.0, 0.5, 0.5},													// black
		new double[] {0.32033485893305097, 0.5742204289459502, 0.29164200916660354},	// blue
		new double[] {0.8733904898896725, 0.40423259756177077, 0.5963696819039991},		// green
		new double[] {0.5423856777952881, 0.5749319939245413, 0.7419635604203512},		// red
		new double[] {0.560453645849854, 0.5249153739617732, 0.5499862119390915},		// brown
		new double[] {0.8056536608960891, 0.5061110640984988, 0.7022800690844268},		// orange
		new double[] {0.9718700296534624, 0.4710022111493074, 0.6932125816353195},		// yellow
		new double[] {0.6120635895385, 0.5589394040639345, 0.40673350251395035},		// purple
		new double[] {0.8953058330600423, 0.4924456389121535, 0.47933271439168756},		// grey
		new double[] {0.9216954542254809, 0.5036809970983391, 0.4934516431636776},		// pink
		new double[] {1.0, 0.4916961902536898, 0.4772823663486011}						// white
	};
	
	
	private Image image;
	
	public void quantize(Image im) {
		if (!(im instanceof LabImage))
			throw new IllegalArgumentException("im should be an instance of LabImage");
		image = im;
	}

	public int getBinsCount() {
		return COLORS.length;
	}

	public Color getBinColor(int i) {
		return new Color(COLORS[i][0]/255.0, COLORS[i][1]/255.0, COLORS[i][2]/255.0);
	}

	public int getBin(int i) {
		double d = 2.0;
		int index = 0;
		double[] comps = image.getColor(i).getComponents();
		for (int j = 0; j < COLORS.length; j++) {
			double cd = distance(comps, LAB_COLORS[j]);
			if (cd < d) {
				d = cd;
				index = j;
			}
		}
		return index;
	}

	private static double distance(double[] c1, double[] c2) {
		double sum = 0.0;
		for (int i = 0; i < 3; i++) {
			double v = c1[i] - c2[i];
			sum += v*v;
		}
		return Math.sqrt(sum); // / Math.sqrt(3);
	}
	
	public String getDescription() {
		return "Focal";
	}

	
	
	// TESTPROGRAMMA
	
	public static void main(String[] args) throws IOException {
		//Image image = new LabImage(
		//	(ImageData.loadFile("/home/klbostee/Thesis/Misc/hsv_constant_v.png")).getRgbImage());
		if (args.length != 2)
			System.out.println("usage: java FocalQuantizer <input image> <output image>");
		try {
			Image image = new LabImage(ImageData.loadFile(args[0]).getRgbImage());
			Quantizer quantizer = new FocalQuantizer();
			quantizer.quantize(image);
			BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					double[] comps = 
						quantizer.getBinColor(quantizer.getBin(y*image.getWidth()+x)).getComponents();
					bi.setRGB(x, y, 
							((int)(comps[0]*255) << 16) | ((int)(comps[1]*255) << 8) | (int)(comps[2]*255));
				}
			}
			//ImageIO.write(bi, "png", new File("/home/klbostee/Thesis/Misc/focal_constant_v.png"));
			ImageIO.write(bi, "png", new File(args[1]));
			System.out.println("done.");
		} catch (IOException e) {
			System.err.println("IO Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
