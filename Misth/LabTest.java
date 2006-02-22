import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.color.ColorSpace;
import de.berlios.imilarity.color.Lab;
import de.berlios.imilarity.color.Xyz;

public class LabTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ColorSpace labCs = new Lab(), xyzCs = new Xyz();
		
		Color c = labCs.fromRgb(new int[] {255,255,0});
		double[] lab = c.getComponents();
		
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
				int[] comps = labCs.toRgb(new Color(0.5, x/255.0, 1-y/255.0));
				image2.setRGB(x,y,(new java.awt.Color(comps[0],comps[1],comps[2])).getRGB());
			}
		}
		ImageIO.write(image2, "png", new File("/home/klbostee/Thesis/Temp/lab.png"));
	}

}
