import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.fuzzy.FuzzyImage;
import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;

public class IntersectImage {

	public static void main(String[] args) throws IOException {
		Image im1 = 
			ImageData.loadFile("/home/klbostee/Images/coil5/obj3__0.png").getRgbImage();
		Image im2 = 
			ImageData.loadFile("/home/klbostee/Images/coil5/obj3__90.png").getRgbImage();
		BufferedImage im = new BufferedImage(im1.getWidth(), im1.getHeight(), BufferedImage.TYPE_INT_RGB);
		FuzzyImage fim1 = new FuzzyImage(im1), fim2 = new FuzzyImage(im2);
		FuzzySet intersection = fim1.intersection(fim2);
		for (int x = 0; x < im1.getWidth(); x++) {
			for (int y = 0; y < im1.getHeight(); y++) {
				double[] comps = intersection.getMembership(im1.getWidth()*y+x).getComponents();
				im.setRGB(x, y, 
					(new Color((int)(comps[0]*255), (int)(comps[1]*255), (int)(comps[2]*255)))
					.getRGB());
			}
		}
		ImageIO.write(im, "png", new File("/home/klbostee/Thesis/Temp/intersection.png"));
	}
}
