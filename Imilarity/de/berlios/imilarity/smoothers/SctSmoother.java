package de.berlios.imilarity.smoothers;


public class SctSmoother implements Smoother {

	private static final int RANGE = 8;
	
	
	private static final double[] GRAY_WEIGHTS = { 
		0.5, 0.25 
	};
	
	private static final double[] COLOR_WEIGHTS = {
		0.14944598078727722, 0.13948291540145874, 0.11332986876368523, 
		0.07999755442142487, 0.04888739436864853, 0.025730207562446594, 
		0.011578593403100967, 0.004410892724990845, 0.0014034658670425415
	};
	
	
	private int gbc, cbc; // gbc = gray bins count, cbc = color bins count

	public SctSmoother(int gbc, int cbc) {
		this.gbc = gbc;
		this.cbc = cbc;
	}
	
	
	public int getRange() {
		return RANGE;
	}

	public int getIndex(int bin, int offset) {
		if (bin < gbc) {
			if (bin+offset < gbc)
				return bin+offset;
			else
				return -1;
		} else {
			if (bin+offset < gbc)
				return bin+offset+cbc;
			else
				return gbc+((bin+offset-gbc)%cbc);
		}
	}

	public double getIncrement(int bin, int offset, double value) {
		int k = Math.abs(offset);
		if (bin < gbc) {
			if (k < GRAY_WEIGHTS.length)
				return GRAY_WEIGHTS[k] * value; // (1.0/Math.pow(3, Math.abs(offset))) * value;
			return 0;
		} else {
			if (k < COLOR_WEIGHTS.length)
				return COLOR_WEIGHTS[k] * value; // (1.0/Math.pow(1.5, Math.abs(offset))) * value;
			return 0;
		}
//		return (1.0/Math.abs(offset))*value;
	}

	public String getDescription() {
		return "SCT smoothing";
	}

	
	
	// PROGRAMMA DAT GEWICHTEN PRINT:
	
	public static void main(String[] args) {
//		for (int i = 0; i <= RANGE; i++)
//			System.out.println("GRAY "+i+": "+(1.0/Math.pow(4, Math.abs(i))));
//		for (int i = 0; i <= RANGE; i++) 
//			System.out.println("COLOR "+i+": "+(1.0/Math.pow(1.5, Math.abs(i))));
		
		int[] c1 = binoms(2);
		double sum1 = 0.0;
		for (int i = 0; i < c1.length; i++)
			sum1 += c1[i];
		for (int i = c1.length/2; i < c1.length; i++)
			System.out.println("GRAY "+i+": "+(c1[i]/sum1));
		
		int[] c2 = binoms(28);
		double sum2 = 0.0;
		for (int i = 0; i < c2.length; i++)
			sum2 += c2[i];
		for (int i = c2.length/2; i < c2.length; i++) 
			System.out.println("COLOR "+i+": "+(c2[i]/sum2));
		
		//TEST:
//		SctSmoother ss = new SctSmoother(16,240);
//		for (int i = -14; i <= 14; i++) {
//			System.out.println("index: " + ss.getIndex(16,i));
//			System.out.println("weight: " + ss.getIncrement(16, i, 1));
//		}
	}
	
	
	private static int[] binoms(int n) {
		if (n == 1)
			return new int[] { 1, 1 };
		else {
			int[] tmp = binoms(n-1);
			int[] res = new int[n+1];
			res[0] = 1;
			res[n] = 1;
			for (int i = 0; i < tmp.length-1; i++)
				res[i+1] = tmp[i] + tmp[i+1];
			return res;
		}
	}
	
//	private static int binom(int n, int k) {
//		return binoms(n)[k];
//	}
}
