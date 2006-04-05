import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.image.ComponentImage;
import de.berlios.imilarity.image.HsvImage;
import de.berlios.imilarity.image.I1i2i3Image;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.IrbImage;
import de.berlios.imilarity.image.LabImage;
import de.berlios.imilarity.image.XyzImage;
import de.berlios.imilarity.image.YxyImage;

public class Scott {

	private static int[] histogram(int factor) {
		int hist[] = new int[factor+1];
		for (int i = 0; i < hist.length; i++)
			hist[i] = 0;
		return hist;
	}
	
	private static void count(Image im, int[] hist) {
    	int pc = im.getWidth()*im.getHeight();
		for (int i = 0; i < pc; i++) {
			if (im.getColor(i).getComponents()[0] > 1)
				System.out.println("Warning: "+im.getColor(i).getComponents()[0]);
			hist[(int)(im.getColor(i).getComponents()[0]*(hist.length-1))]++;
		}
	}
	
	private static BufferedImage drawHistogram(int[] hist) {
		BufferedImage im = new BufferedImage(hist.length, 200, BufferedImage.TYPE_INT_RGB);
		Graphics g = im.getGraphics();
		g.setColor(new Color(220,220,220));
		g.fillRect(0,0,im.getWidth(),im.getHeight());
		g.setColor(Color.BLACK);
//		long count = 0;
//		for (int i = 0; i < hist.length; i++)
//			count += hist[i];
//		count /= 10;
		int max = 0;
		for (int i = 0; i < hist.length; i++)
			if (hist[i] > max) 
				max = hist[i];
		for (int i = 0; i < hist.length; i++)
			if (hist[i] > 0)
				g.drawLine(i, 200-(int)((hist[i]*1.0/max)*200), i, 200);
		g.dispose();
		return im;
	}
	
	private static int binWidth(int[] hist) {
		double m = 0;
		long count = 0;
		for (int i = 0; i < hist.length; i++) {
			m += hist[i]*i;
			count += hist[i];
		}
		m /= count;
		double s = 0;
		for (int i = 0; i < hist.length; i++) {
			double diff = i - m;
			s += hist[i]*diff*diff;
		}
		s /= count - 1; // degree of freedom
		s = Math.sqrt(s);
		return (int)(3.49*s*(Math.pow(count, -1.0/3.0))) + 1;
	}
	
	
	private static void outputData(Image im, int[] hist1, int smallWidth, String name) throws IOException {
		double width = binWidth(hist1);
		System.out.println("Optimal bin width: " + width);
		System.out.println("Optimal number of bins: " + ((hist1.length-1)/width));
		int[] hist2 = histogram((int)((hist1.length-1)/width));
		count(im, hist2);
		BufferedImage im2 = new BufferedImage(hist1.length, 200, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = im2.getGraphics();
		g2.drawImage(drawHistogram(hist2)
				.getScaledInstance(hist1.length, 200, BufferedImage.SCALE_REPLICATE), 0, 0, null);
		g2.dispose();
		ImageIO.write(im2, "png", new File("/home/klbostee/Thesis/Temp3/opt_" + name));
		int[] hist3 = histogram(smallWidth);
		count(im, hist3);
		BufferedImage im3 = new BufferedImage(hist1.length, 200, BufferedImage.TYPE_INT_RGB);
		Graphics g3 = im3.getGraphics();
		g3.drawImage(drawHistogram(hist3)
				.getScaledInstance(hist1.length, 200, BufferedImage.SCALE_REPLICATE), 0, 0, null);
		g3.dispose();
		ImageIO.write(im3, "png", new File("/home/klbostee/Thesis/Temp3/small_" + name));
	}
	
	public static void main(String[] args) throws IOException {
		
		Image im = 
			// ImageData.loadFile("/home/klbostee/Images/coil5/obj3__0.png").getRgbImage();
			ImageData.loadFile("/home/klbostee/Thesis/Misc/flowers.png").getRgbImage();
		String suffix = "flowers.png";
		
		
		// HSV
		
		System.out.println("HSV: ");
		
		Image hsv_im = new HsvImage(im);
		
		Image cim = new ComponentImage(hsv_im, 0);
		int[] hist = histogram(360);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/hsv_hist_h_"+suffix));
		outputData(cim, hist, 16, "hsv_hist_h_"+suffix);
		
		cim = new ComponentImage(hsv_im, 1);
		hist = histogram(100);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/hsv_hist_s_"+suffix));
		outputData(cim, hist, 4, "hsv_hist_s_"+suffix);
		
		cim = new ComponentImage(hsv_im, 2);
		hist = histogram(100);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/hsv_hist_v_"+suffix));
		outputData(cim, hist, 4, "hsv_hist_v_"+suffix);
		
		
		// Irb
		
		System.out.println("Irb: ");
		
		Image irb_im = new IrbImage(im);
		
		cim = new ComponentImage(irb_im, 0);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/irb_hist_i_"+suffix));
		outputData(cim, hist, 4, "irb_hist_i_"+suffix);
		
		cim = new ComponentImage(irb_im, 1);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/irb_hist_r_"+suffix));
		outputData(cim, hist, 8, "irb_hist_r_"+suffix);
		
		cim = new ComponentImage(irb_im, 2);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/irb_hist_b_"+suffix));
		outputData(cim, hist, 8, "irb_hist_b_"+suffix);
		
		
		// I1I2I3
		
		System.out.println("I1I2I3: ");
		
		Image i1i2i3_im = new I1i2i3Image(im);
		
		cim = new ComponentImage(i1i2i3_im, 0);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/i1i2i3_hist_i1_"+suffix));
		outputData(cim, hist, 4, "i1i2i3_hist_i1_"+suffix);
		
		cim = new ComponentImage(i1i2i3_im, 1);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/i1i2i3_hist_i2_"+suffix));
		outputData(cim, hist, 8, "i1i2i3_hist_i2_"+suffix);
		
		cim = new ComponentImage(i1i2i3_im, 2);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/i1i2i3_hist_i3_"+suffix));
		outputData(cim, hist, 8, "i1i2i3_hist_i3_"+suffix);
		
		
		// XYZ
		
		System.out.println("XYZ: ");
		
		Image xyz_im = new XyzImage(im);
		
		cim = new ComponentImage(xyz_im, 0);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/xyz_hist_x_"+suffix));
		outputData(cim, hist, 8, "xyz_hist_x_"+suffix);
		
		cim = new ComponentImage(xyz_im, 1);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/xyz_hist_y_"+suffix));
		outputData(cim, hist, 4, "xyz_hist_y_"+suffix);
		
		cim = new ComponentImage(xyz_im, 2);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/xyz_hist_z_"+suffix));
		outputData(cim, hist, 8, "xyz_hist_z_"+suffix);
		
		
		// Yxy
		
		System.out.println("Yxy: ");
		
		Image yxy_im = new YxyImage(im);
		
		cim = new ComponentImage(yxy_im, 0);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/yxy_hist_Y_"+suffix));
		outputData(cim, hist, 4, "yxy_hist_Y_"+suffix);
		
		cim = new ComponentImage(yxy_im, 1);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/yxy_hist_x_"+suffix));
		outputData(cim, hist, 8, "yxy_hist_x_"+suffix);
		
		cim = new ComponentImage(yxy_im, 2);
		hist = histogram(255);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/yxy_hist_y_"+suffix));
		outputData(cim, hist, 8, "yxy_hist_y_"+suffix);
		
		
		// L*a*b*
		
		System.out.println("L*a*b*: ");
		
		Image lab_im = new LabImage(im);
		
		cim = new ComponentImage(lab_im, 0);
		hist = histogram(100);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/lab_hist_l_"+suffix));
		outputData(cim, hist, 4, "lab_hist_l_"+suffix);
		
		cim = new ComponentImage(lab_im, 1);
		hist = histogram(240);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/lab_hist_a_"+suffix));
		outputData(cim, hist, 8, "lab_hist_a_"+suffix);
		
		cim = new ComponentImage(lab_im, 2);
		hist = histogram(240);
		count(cim, hist);
		ImageIO.write(drawHistogram(hist), "png", 
				new File("/home/klbostee/Thesis/Temp3/lab_hist_b_"+suffix));
		outputData(cim, hist, 8, "lab_hist_b_"+suffix);
	}
}
