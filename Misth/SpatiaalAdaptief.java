
import java.awt.Graphics;
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
	private static final String INPUT_FILE = "beeld_A.png";
	
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
				double value = 3*Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2);
				if (value > 1) value = 1;
				//double value = 1-Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2);
				//value = 1 - value; // temp!!!
				//if (value > 1) value = 1;
				edgesImage.setRGB(x, y, (new java.awt.Color((int)(value*255), (int)(value*255), (int)(value*255))).getRGB());
			}
		}
		
		BufferedImage edgesSobelImage = 
			new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				double l = x > 0 ? gray(image.getColor(x-1,y).getComponents()) : 0; 
				double r = x < w-1 ? gray(image.getColor(x+1,y).getComponents()) : 0; 
				double a = y > 0 ? gray(image.getColor(x,y-1).getComponents()) : 0;
				double b = y < h-1 ? gray(image.getColor(x,y+1).getComponents()) : 0;
				double la = x > 0 && y > 0 ? gray(image.getColor(x-1,y-1).getComponents()) : 0;
				double lb = x > 0 && y < h-1 ? gray(image.getColor(x-1,y+1).getComponents()) : 0;
				double ra = x < w-1 && y > 0 ? gray(image.getColor(x+1,y-1).getComponents()) : 0;
				double rb = x < w-1 && y < h-1 ? gray(image.getColor(x+1,y+1).getComponents()) : 0;
				double v1 = (-la+ra-2*l+2*r-lb+rb)/8.0;
				double v2 = (-la-2*a-ra+lb+2*b+rb)/8.0;
				//double value = Math.pow(1-Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2),3);
				double value = 2*Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2);
				if (value > 1) value = 1;
				//double value = 1-Math.sqrt(v1*v1+v2*v2)/Math.sqrt(2);
				//value = 1 - value; // temp!!!
				//if (value > 1) value = 1;
				edgesSobelImage.setRGB(x, y, (new java.awt.Color((int)(value*255), (int)(value*255), (int)(value*255))).getRGB());
			}
		}
		
		BufferedImage centerImage =
			new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		//double max = Math.sqrt(w*w/4+h*h/4);
		//double max = Math.min(w/2,h/2);
		//double min = 0.3*max;
		//double gamma = Math.sqrt(w*w/4 + h*h/4); //Math.max(w,h);
		double gamma = Math.min(w/2,h/2); // !
		double alpha = 0.7*gamma; // !
		double beta = (gamma+alpha)/2.0;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int v1 = w/2 - x, v2 = h/2 - y;
				double d = Math.sqrt(v1*v1+v2*v2);
				double value;
				if (d < alpha) 				// ]-infinity, alpha[
					value = 1;
				else if (d <= beta) { 		// [alpha, beta]
					value = (d - alpha)/(gamma - alpha);
					value = 1 - 2*value*value;
				} else if (d <= gamma) {	// [beta, gamma]
					value = (d - gamma)/(gamma - alpha);
					value = 2*value*value;
				} else value = 0;			// ]gamma, +infinity[
				centerImage.setRGB(x, y, (new java.awt.Color((int)(value*255), (int)(value*255), (int)(value*255))).getRGB());
			}
		}
		
		BufferedImage centerImage2 =
			new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = centerImage2.getGraphics();
		for (int d = 0; d < h; d++) {
			double value;
			if (d < alpha) 				// ]-infinity, alpha[
				value = 1;
			else if (d <= beta) { 		// [alpha, beta]
				value = (d - alpha)/(gamma - alpha);
				value = 1 - 2*value*value;
			} else if (d <= gamma) {	// [beta, gamma]
				value = (d - gamma)/(gamma - alpha);
				value = 2*value*value;
			} else value = 0;			// ]gamma, +infinity[
			g.drawLine(d, (int)(h-value*h), d, 0); 
		}
		
		BufferedImage spatialImage =
			new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int v1 = edgesImage.getRGB(x,y);
				int v2 = centerImage.getRGB(x,y);
				spatialImage.setRGB(x, y, Math.min(v1,v2));
			}
		}
		
		ImageIO.write(edgesImage, "png", new File(OUTPUT_DIR+"/edges_"+INPUT_FILE));
		ImageIO.write(edgesSobelImage, "png", new File(OUTPUT_DIR+"/edges_sobel_"+INPUT_FILE));
		ImageIO.write(centerImage, "png", new File(OUTPUT_DIR+"/center_"+INPUT_FILE));
		ImageIO.write(centerImage2, "png", new File(OUTPUT_DIR+"/center2_"+INPUT_FILE));
		ImageIO.write(spatialImage, "png", new File(OUTPUT_DIR+"/spatial_"+INPUT_FILE));
		System.out.println("done");
	}

	private static double gray(double[] comps) {
		//return 0.3*comps[0] + 0.59*comps[1] + 0.11*comps[2];
		return (comps[0] + comps[1] + comps[2])/3.0;
	}
}
