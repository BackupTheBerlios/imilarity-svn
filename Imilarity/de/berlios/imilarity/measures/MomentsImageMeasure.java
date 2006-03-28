package de.berlios.imilarity.measures;

import java.awt.image.BufferedImage;

import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;

public class MomentsImageMeasure extends ImageMeasureBase {
	private ImageMeasure measure;
	private int momentsCount;
	
	public MomentsImageMeasure(ImageMeasure measure, int momentsCount) {
		if (measure == null)
			throw new NullPointerException("measure == null");
		this.measure = measure;
		this.momentsCount = momentsCount;
	}
	
	public MomentsImageMeasure(ImageMeasure measure) {
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
		for (int i = 0; i < mean.length; i++) {
			mean[i] /= pc;
			rgb |= (((int)(mean[i]*255) & 0xFF) << (16-i*8));
		}
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
			for (int i = 0; i < moments[j].length; i++) {
				moments[j][i] = Math.pow(moments[j][i]/pc, 1.0/(j+2));
				rgb |= (((int)(moments[j][i]*255) & 0xFF) << (16-i*8));
			}
			res.setRGB(j+1, 0, rgb);
		}
	
		return (new ImageData(res, "intermediate image", null)).getRgbImage();
	}
	
	public String getDescription() {
		return "Moments ("+momentsCount+") using " + measure.getDescription();
	}
}
