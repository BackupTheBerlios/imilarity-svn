import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.image.FocalImage;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.LabImage;

public class FocalColors {

	
	public static Color hsvToColor(double[] comps) {
		double h = comps[0]*6;
		double s = comps[1];
		double v = comps[2];
		//int i = (int) Math.floor(h);
		int i = (int) h;
		double f = h - i;
		if(!((i & 1) == 1)) f = 1 - f; // if i is even
		double m = v * (1 - s);
		double n = v * (1 - s * f);
		switch (i) {
			case 6:
			case 0: return new Color((int)(v*255), (int)(n*255), (int)(m*255));
			case 1: return new Color((int)(n*255), (int)(v*255), (int)(m*255));
			case 2: return new Color((int)(m*255), (int)(v*255), (int)(n*255));
			case 3: return new Color((int)(m*255), (int)(n*255), (int)(v*255));
			case 4: return new Color((int)(n*255), (int)(m*255), (int)(v*255));
			case 5: return new Color((int)(v*255), (int)(m*255), (int)(n*255));
		}
		return Color.BLACK;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedImage bi = new BufferedImage(11,1,BufferedImage.TYPE_INT_RGB);
		bi.setRGB(0, 0, (0 << 16) | (0 << 8) | 0); 			// black	  0	  0   0
		bi.setRGB(1, 0, (0 << 16) | (0 << 8) | 255); 		// blue		  0   0 255
		bi.setRGB(2, 0, (0 << 16) | (255 << 8) | 0); 		// green	  0 255   0
		bi.setRGB(3, 0, (255 << 16) | (0 << 8) | 0); 		// red		255   0   0
		bi.setRGB(4, 0, (128 << 16) | (42 << 8) | 42);	 	// brown	128  42  42
		bi.setRGB(5, 0, (255 << 16) | (128 << 8) | 0);	 	// orange	255 128   0
		bi.setRGB(6, 0, (255 << 16) | (255 << 8) | 0); 		// yellow	255 255   0
		bi.setRGB(7, 0, (160 << 16) | (32 << 8) | 240); 	// purple	160  32 240
		bi.setRGB(8, 0, (192 << 16) | (192 << 8) | 192); 	// gray		192 192 192
		bi.setRGB(9, 0, (255 << 16) | (192 << 8) | 203); 	// pink		255 192 203
		bi.setRGB(10, 0, (255 << 16) | (255 << 8) | 255); 	// white	255 255 255
		ImageIO.write(bi, "png", new File("/home/klbostee/Thesis/Misc/focal_colors.png"));
		
		Image image = new LabImage((new ImageData(bi, "focal colors", null)).getRgbImage());
		for (int i = 0; i < 11; i++) {
			double comps[] = image.getColor(i).getComponents();
			System.out.println(""+comps[0]+", "+comps[1]+", "+comps[2]);
		}
		
		bi = new BufferedImage(512,512,BufferedImage.TYPE_INT_RGB);
		for (int h = 0; h < 512; h++) {
			for (int v = 0; v < 512; v++) {
				Color c = hsvToColor(new double[] { h*1.0/511, 1, v*1.0/511 });
				bi.setRGB(h, v, c.getRGB());
			}
		}
		ImageIO.write(bi, "png", new File("/home/klbostee/Thesis/Misc/hsv_constant_s.png"));
		image = new FocalImage((new ImageData(bi, "focal image", null)).getRgbImage());
		//image = 
		//	new FocalImage(ImageData.loadFile("/home/klbostee/Images/coil5/obj12__0.png").getRgbImage());
		bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				double[] comps = image.getColor(x,y).getComponents();
				int index = (int)(comps[0]*10);
				if (index == 0)
					bi.setRGB(x, y, (0 << 16) | (0 << 8) | 0); 			// black	  0	  0   0
				else if (index == 1)
					bi.setRGB(x, y, (0 << 16) | (0 << 8) | 255); 		// blue		  0   0 255
				else if (index == 2)
					bi.setRGB(x, y, (0 << 16) | (255 << 8) | 0); 		// green	  0 255   0
				else if (index == 3)
					bi.setRGB(x, y, (255 << 16) | (0 << 8) | 0); 		// red		255   0   0
				else if (index == 4)
					bi.setRGB(x, y, (128 << 16) | (42 << 8) | 42);	 	// brown	128  42  42
				else if (index == 5)
					bi.setRGB(x, y, (255 << 16) | (128 << 8) | 0);	 	// orange	255 128   0
				else if (index == 6)
					bi.setRGB(x, y, (255 << 16) | (255 << 8) | 0); 		// yellow	255 255   0
				else if (index == 7)
					bi.setRGB(x, y, (160 << 16) | (32 << 8) | 240); 	// purple	160  32 240
				else if (index == 8)
					bi.setRGB(x, y, (192 << 16) | (192 << 8) | 192); 	// gray		192 192 192
				else if (index == 9)
					bi.setRGB(x, y, (255 << 16) | (192 << 8) | 203); 	// pink		255 192 203
				else if (index == 10)
					bi.setRGB(x, y, (255 << 16) | (255 << 8) | 255); 	// white	255 255 255
			}
		}
		ImageIO.write(bi, "png", new File("/home/klbostee/Thesis/Misc/focal_image.png"));
		System.out.println("done.");
	}
	
}
