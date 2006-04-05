import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.scales.EdgeScale;
import de.berlios.imilarity.scales.SpatialScale;

public class SpatialAlpha {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("/home/klbostee/Thesis/Misc/beeld_B.png"));
		BufferedImage aimage = new BufferedImage(image.getWidth(), image.getHeight(), 
				BufferedImage.TYPE_INT_ARGB);
		aimage.getGraphics().drawImage(image, 0,0,image.getWidth(), image.getHeight(),null);
		//SpatialScale ss = new SpatialScale();
		EdgeScale ss = new EdgeScale();
		ss.setImage((new ImageData(image, "test", null)).getRgbImage());
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Color c = new Color(image.getRGB(x, y));
//				int w = 0;
//				if (ss.getWeight(y*image.getWidth()+x) > 0.001)
//					w = 255;
//				double v = ss.getWeight(y*image.getWidth()+x)*1.8;
//				if (v > 1) v = 1;
				int w = (int)(ss.getWeight(y*image.getWidth()+x)*255);
				aimage.setRGB(x, y, (0xFFFFFF & c.getRGB()) | (w << 24));
			}
		}
		ImageIO.write(aimage, "png", new File("/home/klbostee/Thesis/Misc/edges_alpha_beeld_B.png"));
	}

}
