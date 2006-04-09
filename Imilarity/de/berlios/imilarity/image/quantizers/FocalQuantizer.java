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
	
	private static double[][] LAB_COLORS = {
		new double[] {0.0, 0.5, 0.5}, 													// black
		new double[] {0.32302586667249483, 0.8299734921350065, 0.05060944061530292}, 	// blue
		new double[] {0.8773703347354421, 0.14088152460783507, 0.8466089314376535}, 	// green
		new double[] {0.5323288178584246, 0.8337721962091744, 0.780094924772651}, 		// red
		new double[] {0.557261723746466, 0.6233578811169929, 0.5530186827583052}, 		// brown
		new double[] {0.8026963499063499, 0.5500816497625353, 0.8438452539453778}, 		// orange
		new double[] {0.9713824698129728, 0.4101632824356011, 0.8936988317916194}, 		// yellow
		new double[] {0.6087855220809812, 0.7708109719328161, 0.26403510996680807}, 	// purple
		new double[] {0.8953058330600423, 0.5, 0.5}, 									// grey
		new double[] {0.9206848365087755, 0.546672405826394, 0.5043804130638659}, 		// pink
		new double[] {1.0, 0.5, 0.5233499166677206}, 									// white
	};
	
	
//	public FocalQuantizer() {
//		// berekent de lab-coordinaten van de focale kleuren:
//		ColorSpace lab = new Lab();
//		for (int i = 0; i < COLORS.length; i++) {
//			double[] comps = lab.fromRgb(COLORS[i]).getComponents();
//			LAB_COLORS[i] = comps;
//			for (int j = 0; j < comps.length; j++)
//				System.out.print(""+comps[j]+", ");
//			System.out.println();
//		}
//	}
	
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
			long millis = System.currentTimeMillis();
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
			System.out.println("Time: " + (System.currentTimeMillis()-millis));
			//ImageIO.write(bi, "png", new File("/home/klbostee/Thesis/Misc/focal_constant_v.png"));
			ImageIO.write(bi, "png", new File(args[1]));
			System.out.println("done.");
		} catch (IOException e) {
			System.err.println("IO Error: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	
}
