import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;

import de.berlios.imilarity.fuzzy.ArrayFuzzySet;
import de.berlios.imilarity.fuzzy.FuzzySet;
import de.berlios.imilarity.fuzzy.Membership;
import de.berlios.imilarity.fuzzy.SimpleMembership;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.image.quantizers.NeuQuant;
import de.berlios.imilarity.image.quantizers.Quantizer;
import de.berlios.imilarity.image.quantizers.WuQuantizer;
import de.berlios.imilarity.measures.ComponentsImageMeasure;
import de.berlios.imilarity.measures.FuzzyImageMeasure;
import de.berlios.imilarity.measures.FuzzyMeasure;
import de.berlios.imilarity.measures.ImageMeasure;
import de.berlios.imilarity.measures.ImageMeasureBase;
import de.berlios.imilarity.measures.M1a;
import de.berlios.imilarity.measures.M5;


public class DominantPrint extends ImageMeasureBase {

	private FuzzyMeasure fuzzyMeasure;
	private Quantizer quantizer;
	private int colorCount; 
	
	public DominantPrint(FuzzyMeasure fuzzyMeasure, Quantizer quantizer,
			int colorCount) {
		if (fuzzyMeasure == null)
			throw new NullPointerException("fuzzyMeasure == null");
		if (quantizer == null)
			throw new NullPointerException("quantizer == null");
		this.fuzzyMeasure = fuzzyMeasure;
		this.quantizer = quantizer;
		this.colorCount = colorCount;
	}
	
	public DominantPrint(FuzzyMeasure fuzzyMeasure, Quantizer quantizer) {
		this(fuzzyMeasure, quantizer, quantizer.getBinsCount());
	}
	
	public DominantPrint(FuzzyMeasure fuzzyMeasure) {
		this(fuzzyMeasure, new NeuQuant(30,8));
	}
	
	
	public double getSimilarity() {
		FuzzySet queryColors = calculateColors(getQuery());
		FuzzySet targetColors = calculateColors(getTarget());
		
		System.out.println("Querycolors:");
		double sum = 0, sum1 = 0, sum2 = 0, sum3 = 0;
		for (int i = 0; i < queryColors.getElementsCount(); i++) {
			double[] comps = queryColors.getMembership(i).getComponents();
			for (int j = 0; j < comps.length; j++)
				System.out.print((int)(comps[j]*255) + "\t(" + comps[j] + ")\t");
			System.out.println();
			
			sum += queryColors.getMembership(i).abs();
			System.out.println("+ "+queryColors.getMembership(i).abs());
			sum1 += comps[0];
			sum2 += comps[1];
			sum3 += comps[2];
		}
		System.out.println("tralie: "+sum+" c1: " +sum1+" c2: "+sum2+" c3: "+sum3);
		
		System.out.println("TargetColors:");
		sum = 0; sum1 = 0; sum2 = 0; sum3 = 0;
		for (int i = 0; i < targetColors.getElementsCount(); i++) {
			double[] comps = targetColors.getMembership(i).getComponents();
			for (int j = 0; j < comps.length; j++)
				System.out.print((int)(comps[j]*255) + "\t(" + comps[j] + ")\t");
			System.out.println();
			
			sum += queryColors.getMembership(i).abs();
			sum1 += comps[0];
			sum2 += comps[1];
			sum3 += comps[2];
		}
		System.out.println("tralie: "+sum+" c1: " +sum1+" c2: "+sum2+" c3: "+sum3);
		
		fuzzyMeasure.setQuery(queryColors);
		fuzzyMeasure.setTarget(targetColors);
		return fuzzyMeasure.getSimilarity();
	}
	
	private FuzzySet calculateColors(Image image) {
		quantizer.quantize(image);
		int bc = quantizer.getBinsCount();
		ArrayFuzzySet colors = new ArrayFuzzySet(bc);
		//List colors = new ArrayList();
		
		final HashMap freqs = new HashMap();
		for (int i = 0; i < bc; i++) {
			double[] color = quantizer.getBinColor(i).getComponents();
			Membership m = new EqMembership(color);
			colors.addMembership(i, m);
			//colors.add(m);
			freqs.put(m, new Frequency());
 		}
		
		int pc = image.getWidth() * image.getHeight();
		for (int i = 0; i < pc; i++) {
			double[] color = quantizer.getBinColor(quantizer.getBin(i)).getComponents();
			((Frequency)freqs.get(new EqMembership(color))).value++;
		}
		colors.sort(new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Frequency f1 = (Frequency) freqs.get(arg0);
				Frequency f2 = (Frequency) freqs.get(arg1);
				return (new Double(f2.value)).compareTo(new Double(f1.value));
			}
		});
		return colors.head(colorCount);
	}
	
	private class EqMembership extends SimpleMembership {
		public EqMembership(double[] components) {
			super(components);
		}
		public boolean equals(Object o) {
			return equals((Membership) o);
		}
		public int hashCode() {
			return (int) (abs() * 10000);
		}
	}
	
	private static class Frequency {
		public int value = 0;
	}

	
	public String getDescription() {
		return "Fuzzy Quantized using " + fuzzyMeasure.getDescription() + " and "
			+ quantizer.getDescription();
	}
	
	
	public static void main(String[] args) throws IOException {
		ImageMeasure dp = new DominantPrint(
//		MultiresImageMeasure mp = new MultiresImageMeasure(		
				new M5(), new WuQuantizer(8), 8);
		dp.setQuery(ImageData.loadFile("/home/klbostee/Workspace/Thesis/images/beeld_A.png").getRgbImage());
		//dp.setQuery(ImageData.loadFile("/home/klbostee/Images/coil5/obj3__0.png").getRgbImage());
		dp.setTarget(ImageData.loadFile("/home/klbostee/Workspace/Thesis/images/beeld_B.png").getRgbImage());
		//dp.setTarget(ImageData.loadFile("/home/klbostee/Images/coil5/obj3__90.png").getRgbImage());
		double sim = dp.getSimilarity();
		System.out.println("Global sim: " + sim);
	}

}
