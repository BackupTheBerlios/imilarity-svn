
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;

public class SpatiaalAdaptief {
	
	private static final String OUTPUT_DIR = "/home/klbostee/Thesis/Misc";
	//private static final String INPUT_DIR = "/home/klbostee/Images/coil5";
	private static final String INPUT_DIR = "/home/klbostee/Thesis/Misc";
	private static final String INPUT_FILE = "flowers.png";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ImageData imageData = ImageData.loadFile(INPUT_DIR+"/"+INPUT_FILE);
		Image image = imageData.getRgbImage();
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		BufferedImage edgesImage = 
			new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				double l = x > 0 ? gray(image.getColor(x-1,y).getComponents()) : 0; 
				double r = x < w-1 ? gray(image.getColor(x+1,y).getComponents()) : 0; 
				double a = y > 0 ? gray(image.getColor(x,y-1).getComponents()) : 0;
				double b = y < h-1 ? gray(image.getColor(x,y+1).getComponents()) : 0;
				double v1 = (r-l)/2.0, v2 = (b-a)/2.0;
				double value = 1 - Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2);
				//if (value > 1) value = 1;
				edgesImage.setRGB(x, y, (new java.awt.Color((int)(value*255), (int)(value*255), (int)(value*255))).getRGB());
			}
		}
		
		BufferedImage centreImage =
			new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		//double max = Math.sqrt(w*w/4+h*h/4);
		double max = Math.min(w/2,h/2);
		double r = 0.2*max;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int v1 = w/2 - x, v2 = h/2 - y;
				double d = Math.sqrt(v1*v1+v2*v2);
				double value = d > r ? (d-r)/(max-r) : 0;
				if (value > 1) value = 1;
				value = 1 - Math.pow(value,0.8);
				centreImage.setRGB(x, y, (new java.awt.Color((int)(value*255), (int)(value*255), (int)(value*255))).getRGB());
			}
		}
		
		BufferedImage spatialImage =
			new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int v1 = edgesImage.getRGB(x,y);
				int v2 = centreImage.getRGB(x,y);
				spatialImage.setRGB(x, y, Math.min(v1,v2));
			}
		}
		
		ImageIO.write(edgesImage, "png", new File(OUTPUT_DIR+"/edges_"+INPUT_FILE));
		ImageIO.write(centreImage, "png", new File(OUTPUT_DIR+"/centre_"+INPUT_FILE));
		ImageIO.write(spatialImage, "png", new File(OUTPUT_DIR+"/spatial_"+INPUT_FILE));
		System.out.println("done");
	}

	private static double gray(double[] comps) {
		return 0.3*comps[0] + 0.59*comps[1] + 0.11*comps[2];
	}
}
