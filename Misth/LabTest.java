import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.color.ColorSpace;
import de.berlios.imilarity.color.I1i2i3;
import de.berlios.imilarity.color.Lab;
import de.berlios.imilarity.color.Xyz;

public class LabTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ColorSpace labCs = new Lab(), xyzCs = new Xyz(), i1i2i3Cs = new I1i2i3();
		
		Color c = labCs.fromRgb(new int[] {128, 42, 42});
		double[] lab = c.getComponents();
		
		System.out.println("bruin: l="+lab[0]+" a="+lab[1]+" b="+lab[2]);
		
		c = labCs.fromRgb(new int[] {160, 32, 240});
		lab = c.getComponents();
		
		System.out.println("paars: l="+lab[0]+" a="+lab[1]+" b="+lab[2]);
		
		c = labCs.fromRgb(new int[] {36,66,136});
		lab = c.getComponents();
		
		System.out.println("l="+lab[0]+" a="+lab[1]+" b="+lab[2]);
		
		
		int[] rgb = labCs.toRgb(new Color(1.0,0.5,1.0));
		
		System.out.println("r="+rgb[0]+" g="+rgb[1]+" b="+rgb[2]);
		
		
		BufferedImage image1 = new BufferedImage(255,255,BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < 255; x++) {
			for (int y = 0; y < 255; y++) {
				int[] comps = xyzCs.toRgb(new Color(x/255.0, 0.5, y/255.0));
				image1.setRGB(x,y,(new java.awt.Color(comps[0],comps[1],comps[2])).getRGB());
			}
		}
		ImageIO.write(image1, "png", new File("/home/klbostee/Thesis/Temp/xyz.png"));
		
		BufferedImage image2 = new BufferedImage(255,255,BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < 255; x++) {
			for (int y = 0; y < 255; y++) {
				int[] comps = labCs.toRgb(new Color(0.59, x/255.0, 1-y/255.0));
//				if (Math.abs(comps[0]-128) < 10 && 
//				    Math.abs(comps[1] - 42) < 10 && 
//				    Math.abs(comps[2] - 42) < 10)
//					image2.setRGB(x,y,java.awt.Color.WHITE.getRGB());
//				else if (Math.abs(comps[0] - 36) < 5 && 
//					     Math.abs(comps[1] - 66) < 5 && 
//					     Math.abs(comps[2] - 136) < 5)
//					image2.setRGB(x,y,java.awt.Color.RED.getRGB());
//				else if (Math.abs(comps[0] - 0) < 35 && 
//					     Math.abs(comps[1] - 0) < 35 && 
//					     Math.abs(comps[2] - 255) < 35)
//					image2.setRGB(x,y,java.awt.Color.WHITE.getRGB());
//				else if (Math.abs(comps[0] - 160) < 9 && 
//					     Math.abs(comps[1] - 32) < 9 && 
//					     Math.abs(comps[2] - 240) < 9)
//					image2.setRGB(x,y,java.awt.Color.WHITE.getRGB());
//				else
					image2.setRGB(x,y,(new java.awt.Color(comps[0],comps[1],comps[2])).getRGB());
			}
		}
		ImageIO.write(image2, "png", new File("/home/klbostee/Thesis/Temp/lab.png"));
		
		BufferedImage image3 = new BufferedImage(255,255,BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < 255; x++) {
			for (int y = 0; y < 255; y++) {
				int[] comps = i1i2i3Cs.toRgb(new Color(1, x/255.0, 1-y/255.0));
//				if (Math.abs(comps[0]-128) < 10 && 
//				    Math.abs(comps[1] - 42) < 10 && 
//				    Math.abs(comps[2] - 42) < 10)
//					image3.setRGB(x,y,java.awt.Color.WHITE.getRGB());
//				else if (Math.abs(comps[0] - 36) < 5 && 
//					     Math.abs(comps[1] - 66) < 5 && 
//					     Math.abs(comps[2] - 136) < 5)
//					image3.setRGB(x,y,java.awt.Color.RED.getRGB());
//				else if (Math.abs(comps[0] - 0) < 35 && 
//					     Math.abs(comps[1] - 0) < 35 && 
//					     Math.abs(comps[2] - 255) < 35)
//					image3.setRGB(x,y,java.awt.Color.WHITE.getRGB());
//				else if (Math.abs(comps[0] - 160) < 9 && 
//					     Math.abs(comps[1] - 32) < 9 && 
//					     Math.abs(comps[2] - 240) < 9)
//					image3.setRGB(x,y,java.awt.Color.WHITE.getRGB());
//				else
					image3.setRGB(x,y,(new java.awt.Color(comps[0],comps[1],comps[2])).getRGB());
			}
		}
		ImageIO.write(image3, "png", new File("/home/klbostee/Thesis/Temp/i1i2i3.png"));
	}

}
