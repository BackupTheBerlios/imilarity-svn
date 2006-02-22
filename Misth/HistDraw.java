import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


import de.berlios.imilarity.color.ColorSpace;
import de.berlios.imilarity.color.Hsv;
import de.berlios.imilarity.color.I1i2i3;
import de.berlios.imilarity.color.Irb;
import de.berlios.imilarity.color.Lab;
import de.berlios.imilarity.color.Xyz;
import de.berlios.imilarity.color.Yxy;
import de.berlios.imilarity.fuzzy.FuzzyHistogram;
import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.PseudoFuzzyHistogram;
import de.berlios.imilarity.image.FocalImage;
import de.berlios.imilarity.image.GrayscaleImage;
import de.berlios.imilarity.image.HsvImage;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.IrbImage;
import de.berlios.imilarity.image.LabImage;
import de.berlios.imilarity.image.I1i2i3Image;
import de.berlios.imilarity.image.XyzImage;
import de.berlios.imilarity.image.YxyImage;
import de.berlios.imilarity.image.quantizers.Quantizer;
import de.berlios.imilarity.image.quantizers.SctQuantizer;
import de.berlios.imilarity.image.quantizers.UniformQuantizer;
import de.berlios.imilarity.smoothers.DefaultSmoother;
import de.berlios.imilarity.smoothers.SctSmoother;
import de.berlios.imilarity.smoothers.Smoother;


public class HistDraw {
	
	public static PseudoFuzzyHistogram calculateHistogram(Image image, Quantizer quantizer, Smoother smoother) {
		Map histogram = new HashMap();
		int pc = image.getWidth() * image.getHeight();
		
//		int histLength = binsCounts[0];
//		for (int i = 1; i < image.getColorComponentsCount(); i++)
//			histLength *= binsCounts[i];
//		//int maxIndex = 0;
//		for (int i = 0; i < pc; i++) {
//			double[] comps = image.getColor(i).getComponents();
//			int v1 = Math.min((int)(comps[0]*binsCounts[0]),binsCounts[0]-1);
//			int factor = 1;
//			for (int j = 1; j < comps.length; j++) {
//				factor *= binsCounts[j-1];
//				v1 += factor * Math.min((int)(comps[j]*binsCounts[j]),binsCounts[j]-1);
//			}
//			
//			Integer index = new Integer(v1);
//			Integer prev = (Integer)histogram.get(index);
//			if (prev == null) prev = new Integer(0);
//			
//			int newValue = prev.intValue()+1;
//			histogram.put(index, new Integer(newValue));
//			//if (newValue > maxIndex) maxIndex = v1;
//		}
//		return new PseudoFuzzyHistogram(histogram,histLength,smoother);
		
		quantizer.quantize(image);
		int histLength = quantizer.getBinsCount();
		for (int i = 0; i < pc; i++) {
			Integer index = new Integer(quantizer.getBin(i));
			Integer oldValue = (Integer) histogram.get(index);
			if (oldValue == null) oldValue = new Integer(0);
			Integer newValue = new Integer(oldValue.intValue()+1);
			histogram.put(index, newValue);
		}
		return new PseudoFuzzyHistogram(histogram, histLength, smoother);
	}
	
	private static final int HIST_HEIGHT = 200, SPACING = 4, BAR_HEIGHT = 10;
	
	public static BufferedImage drawHistogram(FuzzySet histogram, Quantizer quantizer, ColorFactory cf) {
		int count = histogram.getElementsCount();
		BufferedImage histImage = 
			new BufferedImage(count,HIST_HEIGHT+SPACING+BAR_HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g = histImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0,count,HIST_HEIGHT+SPACING+BAR_HEIGHT);
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < histogram.getElementsCount(); i++) {
			//System.out.print("membership="+histogram.getMembership(i).abs()+" -> ");
			//System.out.println("x1="+i+" y1="+(100-((int)(histogram.getMembership(i).abs()*100)))+
			//		" x2="+i+" y2="+100);
			
//			double[] comps = new double[binsCounts.length];
//			
//			int l = binsCounts[0];
//			for (int j = 1; j < binsCounts.length; j++)
//				l *= binsCounts[j];
//			
//			int tmp = i;
//			for (int j = binsCounts.length-1; j >= 0; j--) {
//				l /= binsCounts[j];
//				double top = ((tmp/l)+1) * 1.0 / binsCounts[j];
//				double bottom = (tmp/l) * 1.0 / binsCounts[j];
//				//comps[j] =  (tmp/l) * 1.0 / binsCounts[j];
//				//System.out.print("top = " + top + " bottom = " + bottom);
//				comps[j] = (bottom + top)/2;
//				//System.out.print(" c"+(j+1)+"="+comps[j]);
//				tmp %= l;
//			}
			//System.out.println();
			
			g.setColor(cf.createColor(quantizer.getBinColor(i).getComponents()));
			
			double m = histogram.getMembership(i).abs();
			if (m > 0)
				g.drawLine(i, HIST_HEIGHT-((int)(m*HIST_HEIGHT)), i, HIST_HEIGHT);
			
			g.drawLine(i, HIST_HEIGHT+SPACING, i, HIST_HEIGHT+SPACING+BAR_HEIGHT);
		}
		
		g.dispose();
		return histImage;
	}
	
	public static BufferedImage drawHistogram(Image image, Quantizer quantizer, ColorFactory cf, 
			Smoother smoother) {
		return drawHistogram(calculateHistogram(image, quantizer, smoother), quantizer, cf);
	}
	
	public static BufferedImage drawHistogram(Image image, int[] binsCounts, ColorFactory cf, 
			Smoother smoother) {
		return drawHistogram(image, new UniformQuantizer(binsCounts), cf, smoother);
	}
	
	public static BufferedImage drawHistogram(Image image, Quantizer quantizer, ColorFactory cf) {
		return drawHistogram(image, quantizer, cf, new DefaultSmoother());
	}
	
	public static BufferedImage drawHistogram(Image image, int[] binsCounts, ColorFactory cf) {
		return drawHistogram(image, binsCounts, cf, new DefaultSmoother());
	}
	
	
	
	private static interface ColorFactory {
		public Color createColor(double[] comps);
	}
	
	private static class RgbFactory implements ColorFactory {
		public Color createColor(double[] comps) {
			return new Color((int)(comps[0]*255), (int)(comps[1]*255), (int)(comps[2]*255));
		}
	}
	
	private static class GrayscaleFactory implements ColorFactory {
		public Color createColor(double[] comps) {
			return new Color((int)(comps[0]*255), (int)(comps[0]*255), (int)(comps[0]*255));
		}
	}
	
	private static class ColorSpaceFactory implements ColorFactory {
		private ColorSpace cs;
		public ColorSpaceFactory(ColorSpace cs) {
			this.cs = cs;
		}
		public Color createColor(double[] comps) {
			int[] rgb = cs.toRgb(new de.berlios.imilarity.color.Color(comps));
			return new Color(rgb[0], rgb[1], rgb[2]);
		}
	}
	
	private static class FocalFactory implements ColorFactory {
		private Quantizer quantizer;
		public FocalFactory(Quantizer quantizer) {
			this.quantizer = quantizer;
		}
		public Color createColor(double[] comps) {
			int index = (int)(comps[0]*11);
			double[] rgb = quantizer.getBinColor(index).getComponents();
			return new Color((int)(rgb[0]*255),(int)(rgb[1]*255),(int)(rgb[2]*255));
		}
	}
	
	private static class SctFactory extends ColorSpaceFactory {
		private Quantizer quantizer;
		public SctFactory(Quantizer quantizer) {
			super(new Hsv());
			this.quantizer = quantizer;
		}
		
		public Color createColor(double[] comps) {
			int index = (int)(comps[0]*256);
			return super.createColor(quantizer.getBinColor(index).getComponents());
		}
	}
	
	/*
	private static class HsvFactory implements ColorFactory {
		public Color createColor(double[] comps) {
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
	}
	
	private static class FocalFactory implements ColorFactory {
		public Color createColor(double[] comps) {
			int index = (int)(comps[0]*11);
			if (index == 0)
				return new Color((0 << 16) | (0 << 8) | 0); 		// black	  0	  0   0
			else if (index == 1)
				return new Color((0 << 16) | (0 << 8) | 255); 		// blue		  0   0 255
			else if (index == 2)
				return new Color((0 << 16) | (255 << 8) | 0); 		// green	  0 255   0
			else if (index == 3)
				return new Color((255 << 16) | (0 << 8) | 0); 		// red		255   0   0
			else if (index == 4)
				return new Color((128 << 16) | (42 << 8) | 42);	 	// brown	128  42  42
			else if (index == 5)
				return new Color((255 << 16) | (128 << 8) | 0);	 	// orange	255 128   0
			else if (index == 6)
				return new Color((255 << 16) | (255 << 8) | 0); 	// yellow	255 255   0
			else if (index == 7)
				return new Color((160 << 16) | (32 << 8) | 240); 	// purple	160  32 240
			else if (index == 8)
				return new Color((192 << 16) | (192 << 8) | 192); 	// gray		192 192 192
			else if (index == 9)
				return new Color((255 << 16) | (192 << 8) | 203); 	// pink		255 192 203
			else
				return new Color((255 << 16) | (255 << 8) | 255); 	// white	255 255 255
		}
	}
	
	private static class NhFactory extends HsvFactory {
		private int gbc, cbc;
		
		public NhFactory(SctImage nhi) {
			gbc = nhi.getGrayscaleBinsCount();
			cbc = nhi.getColorBinsCount();
		}
		
		public Color createColor(double[] comps) {
			if (comps[0] <= (gbc-1)*1.0/(gbc+cbc-1))
				return super.createColor(new double[] {0, 0, (comps[0]*(gbc+cbc-1))/(gbc-1)});
			else
				return super.createColor(new double[] {(((comps[0]*(gbc+cbc-1))-gbc) + 1)/cbc, 1, 1});
		}
	}
	
	private static class OcsFactory implements ColorFactory {
		public Color createColor(double[] comps) {
			double r = comps[0] + comps[1] - (2.0/3.0)*comps[2] - (5.0/6.0);
			double g = comps[0] - (4.0/3.0)*comps[2] + (2.0/3.0);
			double b = comps[0] - comps[1] + (2.0/3.0)*comps[2] + (1.0/6.0);
			if (r < 0) r = 0;
			else if (r > 1) r = 1;
			if (g < 0) g = 0;
			else if (g > 1) g = 1;
			if (b < 0) b = 0;
			else if (b > 1) b = 1;
			return new Color((int)(r*255),(int)(g*255),(int)(b*255));
		}
	}
	
	private static class XyzFactory implements ColorFactory {
		public Color createColor(double[] comps) {
			double r = 3.059589890 * comps[0] - 0.4746773243 * comps[2] - 1.392746380 * comps[1];
			double g = 0.04166582988 * comps[2] + 1.874840829 * comps[1] - 0.9676287255 * comps[0];
			double b = -0.2298981685 * comps[1] + 0.06879652450 * comps[0] + 1.069304567 * comps[2];
			if (r < 0) r = 0;
			else if (r > 1) r = 1;
			if (g < 0) g = 0;
			else if (g > 1) g = 1;
			if (b < 0) b = 0;
			else if (b > 1) b = 1;
			return new Color((int)(r*255),(int)(g*255),(int)(b*255));
		}
	}
	
	private static class LabFactory extends XyzFactory {
		private static final double d = 6.0/29.0;
		public Color createColor(double[] comps) {
			double x, y, z;
			double fy = (comps[0]*100 + 16)/116;
			double fx = fy + (comps[1]*1000 - 500)/500;
			double fz = fy - (comps[2]*400 - 200)/200;
			if (fy > d) y = fy*fy*fy;
			else 		y = (fy - 16.0/116.0)*3*d*d;
			if (fx > d) x = fx*fx*fx;
			else		x = (fx - 16.0/116.0)*3*d*d;
			if (fz > d) z = fz*fz*fz;
			else		z = (fz - 16.0/116.0)*3*d*d;
			return super.createColor(new double[] {x,y,z});
		}
	}
	
	private static class IrbFactory implements ColorFactory {
		public Color createColor(double[] comps) {
			double r = 3*comps[1]*comps[0];
			double g = 3*comps[0] - 3*comps[1]*comps[0] - 3*comps[2]*comps[0];
			double b = 3*comps[2]*comps[0];
			if (r < 0) r = 0;
			else if (r > 1) r = 1;
			if (g < 0) g = 0;
			else if (g > 1) g = 1;
			if (b < 0) b = 0;
			else if (b > 1) b = 1;
			return new Color((int)(r*255),(int)(g*255),(int)(b*255));
		}
	}
	
	private static class YxyFactory extends XyzFactory {
		public Color createColor(double[] comps) {
			double x = comps[1] * comps[0] / comps[2];
			double y = comps[0];
			double z = - comps[0] * (comps[1]-1+comps[2]) / comps[2];
			return super.createColor(new double[] {x,y,z});
		}
	}
	*/
	
	private static final String OUTPUT_DIR = "/home/klbostee/Thesis/Histograms";
	
	public static void drawHistograms(String file) throws IOException {
		
		String name = file.substring(file.lastIndexOf('/')+1);
		Image image = ImageData.loadFile(file).getRgbImage();
		
		
		ImageIO.write(drawHistogram(image, new int[] {7,7,7}, new RgbFactory()), "png", 
				new File(OUTPUT_DIR+"/rgb_"+name));
		System.out.println("rgb done.");
			
		ImageIO.write(drawHistogram(new GrayscaleImage(image), new int[] {256}, new GrayscaleFactory()), 
				"png", new File(OUTPUT_DIR+"/grayscale_"+name));
		System.out.println("grayscale done.");
		
		ImageIO.write(drawHistogram(new HsvImage(image), new int[] {16,4,4}, 
				new ColorSpaceFactory(new Hsv())), 
				"png", new File(OUTPUT_DIR+"/hsv_"+name));
		System.out.println("hsv done.");
		
		ImageIO.write(drawHistogram(new I1i2i3Image(image), new int[] {4,8,8}, 
				new ColorSpaceFactory(new I1i2i3())), 
				"png", new File(OUTPUT_DIR+"/i1i2i3_"+name));
		System.out.println("i1i2i3 done.");
		
		ImageIO.write(drawHistogram(new LabImage(image), new int[] {4,8,8}, 
				new ColorSpaceFactory(new Lab())), 
				"png", new File(OUTPUT_DIR+"/lab_"+name));
		System.out.println("lab done.");
		
		ImageIO.write(drawHistogram(new IrbImage(image), new int[] {4,8,8}, 
				new ColorSpaceFactory(new Irb())), 
				"png", new File(OUTPUT_DIR+"/irb_"+name));
		System.out.println("irb done.");
		
		ImageIO.write(drawHistogram(new YxyImage(image), new int[] {4,8,8}, 
				new ColorSpaceFactory(new Yxy())), 
				"png", new File(OUTPUT_DIR+"/yxy_"+name));
		System.out.println("yxy done.");

		FocalImage fi = new FocalImage(new LabImage(image));
		ImageIO.write(drawHistogram(fi, new int[] {11}, 
				new FocalFactory(fi.getQuantizer())), 
				"png", new File(OUTPUT_DIR+"/focal_"+name));
		System.out.println("focal done.");
		
		HsvImage nhi = new HsvImage(image);
		Quantizer sctQuant = new SctQuantizer(6,250);
		ImageIO.write(
				drawHistogram(
						nhi, 
						sctQuant, 
						new SctFactory(sctQuant)
					), 
				"png", new File(OUTPUT_DIR+"/sct_"+name));
		System.out.println("sct done.");
		ImageIO.write(
				drawHistogram(
						nhi, 
						sctQuant, 
						new SctFactory(sctQuant),
						new SctSmoother(6, 250)
					), 
				"png", new File(OUTPUT_DIR+"/smoothed_sct_"+name));
		System.out.println("smoothed sct done.");
		
		ImageIO.write(drawHistogram(new XyzImage(image), new int[] {8,4,8}, 
				new ColorSpaceFactory(new Xyz())), 
				"png", new File(OUTPUT_DIR+"/xyz_"+name));
		System.out.println("xyz done.");
		
		
		Quantizer quantizer = new UniformQuantizer(new int[] {8,8,8});
		FuzzyHistogram fh = new FuzzyHistogram(new LabImage(image), quantizer);
		ColorFactory cf = new ColorSpaceFactory(new Lab());
		ImageIO.write(drawHistogram(fh, new UniformQuantizer(new int[] {7,7,7}), 
				new ColorSpaceFactory(new Lab())), "png", new File(OUTPUT_DIR+"/fuzzy_"+name));
		
		
		int count = fh.getElementsCount();
		BufferedImage histImage = 
			new BufferedImage(count,HIST_HEIGHT+SPACING+BAR_HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g = histImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0,count,HIST_HEIGHT+SPACING+BAR_HEIGHT);
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < fh.getElementsCount(); i++) {
			
			g.setColor(cf.createColor(quantizer.getBinColor(i).getComponents()));
			
			double m = fh.getMembership(i).abs();
			if (m > 0)
				g.drawLine(i, HIST_HEIGHT-((int)(m*HIST_HEIGHT)), i, HIST_HEIGHT);
			
			g.drawLine(i, HIST_HEIGHT+SPACING, i, HIST_HEIGHT+SPACING+BAR_HEIGHT);
		}
		
		g.dispose();
		ImageIO.write(histImage, "png", new File(OUTPUT_DIR+"/fuzzy_"+name));
		System.out.println("fuzzy done");
		
	}

	
	
	public static void main(String[] args) throws IOException {
		
		String objname = "obj43";
		drawHistograms("/home/klbostee/Thesis/Histograms/"+objname+"__0.png");
		drawHistograms("/home/klbostee/Thesis/Histograms/"+objname+"__45.png");
		drawHistograms("/home/klbostee/Thesis/Histograms/"+objname+"__90.png");
		drawHistograms("/home/klbostee/Thesis/Histograms/"+objname+"__180.png");
		drawHistograms("/home/klbostee/Thesis/Histograms/"+objname+"__270.png");
		drawHistograms("/home/klbostee/Thesis/Histograms/"+objname+"__315.png");
		
		//drawHistograms("/home/klbostee/Thesis/Misc/flowers.png");
	}
}
