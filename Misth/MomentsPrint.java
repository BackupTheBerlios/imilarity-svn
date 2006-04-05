import java.awt.image.BufferedImage;
import java.io.IOException;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.quantizers.WuQuantizer;
import de.berlios.imilarity.measures.FuzzyImageMeasure;
import de.berlios.imilarity.measures.ImageMeasure;
import de.berlios.imilarity.measures.ImageMeasureBase;
import de.berlios.imilarity.measures.M5;
import de.berlios.imilarity.measures.M9;

public class MomentsPrint extends ImageMeasureBase {
	private ImageMeasure measure;
	private int momentsCount;
	
	public MomentsPrint(ImageMeasure measure, int momentsCount) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		this.momentsCount = momentsCount;
	}
	
	public MomentsPrint(ImageMeasure measure) {
		this(measure, 2);
	}
	
	
	public void setQuery(Image image) {
		Image queryColors = calculateIntermediateImage(image);
		measure.setQuery(queryColors);
		super.setQuery(queryColors);
	}
	
	public void setTarget(Image image) {
		Image targetColors = calculateIntermediateImage(image);
		measure.setTarget(targetColors);
		super.setTarget(targetColors);
	}
	
	public double getSimilarity() {
		return measure.getSimilarity();
	}
	
	private Image calculateIntermediateImage(Image image) {
		BufferedImage res = new BufferedImage(1+momentsCount, 1, BufferedImage.TYPE_INT_RGB);
		int pc = image.getWidth()*image.getHeight();
		
		double[] mean = new double[image.getColorComponentsCount()];
		for (int i = 0; i < pc; i++) {
			double[] comps = image.getColor(i).getComponents();
			for (int j = 0; j < comps.length; j++)
				mean[j] += comps[j];
		}
		int rgb = 0;
		System.out.print("mean: ");
		for (int i = 0; i < mean.length; i++) {
			mean[i] /= pc;
			System.out.print(""+(int)(mean[i]*255)+" ");
			rgb |= (((int)(mean[i]*255) & 0xFF) << (16-i*8));
		}
		System.out.println();
		res.setRGB(0, 0, rgb);
		
		double[][] moments = new double[momentsCount][image.getColorComponentsCount()];
		for (int i = 0; i < pc; i++) {
			double[] comps = image.getColor(i).getComponents();
			for (int j = 0; j < comps.length; j++) {
				double diff = comps[j] - mean[j];
				for (int k = 0; k < momentsCount; k++) {
					diff *= diff;
					moments[k][j] += diff;
				}
			}
		}
		for (int j = 0; j < momentsCount; j++) {
			rgb = 0;
			System.out.print("moment "+j+": ");
			for (int i = 0; i < moments[j].length; i++) {
				moments[j][i] = Math.pow(moments[j][i]/pc, 1.0/(j+2));
				System.out.print(""+(int)(moments[j][i]*255)+" ");
				rgb |= (((int)(moments[j][i]*255) & 0xFF) << (16-i*8));
			}
			System.out.println();
			res.setRGB(j+1, 0, rgb);
		}
	
		return (new ImageData(res, "intermediate image", null)).getRgbImage();
	}
	
	public String getDescription() {
		return "Moments ("+momentsCount+") using " + measure.getDescription();
	}

	public static void main(String[] args) throws IOException {
		ImageMeasure dp = new MomentsPrint(	
				new FuzzyImageMeasure(new M9()));
		dp.setQuery(ImageData.loadFile("/home/klbostee/Workspace/Thesis/images/beeld_A.png").getRgbImage());
		//dp.setQuery(ImageData.loadFile("/home/klbostee/Images/coil5/obj3__0.png").getRgbImage());
		dp.setTarget(ImageData.loadFile("/home/klbostee/Workspace/Thesis/images/beeld_B.png").getRgbImage());
		//dp.setTarget(ImageData.loadFile("/home/klbostee/Images/coil5/obj3__90.png").getRgbImage());
		double sim = dp.getSimilarity();
		System.out.println("Global sim: " + sim);
	}
}
