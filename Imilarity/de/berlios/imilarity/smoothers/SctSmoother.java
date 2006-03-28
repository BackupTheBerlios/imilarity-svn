package de.berlios.imilarity.smoothers;


public class SctSmoother implements Smoother {

	private static final int RANGE = 14;
	
	private static final double[] GRAY_WEIGHTS = {
		1.0, 0.3333333333333333, 0.1111111111111111, 0.037037037037037035, 0.012345679012345678,
		0.00411522633744856, 0.0013717421124828531
	};
	
	private static final double[] COLOR_WEIGHTS = {
		1.0, 0.6666666666666666, 0.4444444444444444, 0.2962962962962963, 0.19753086419753085,
		0.13168724279835392, 0.0877914951989026, 0.05852766346593507, 0.03901844231062338,
		0.02601229487374892, 0.017341529915832612, 0.011561019943888409, 0.0077073466292589396,
		0.005138231086172626, 0.0034254873907817508
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
		for (int i = 0; i <= RANGE; i++)
			System.out.println("GRAY "+i+": "+(1.0/Math.pow(3, Math.abs(i))));
		for (int i = 0; i <= RANGE; i++) 
			System.out.println("COLOR "+i+": "+(1.0/Math.pow(1.5, Math.abs(i))));
		
//		TEST:
//		SctSmoother ss = new SctSmoother(16,240);
//		for (int i = -6; i <= 6; i++)
//			System.out.println("index: " + ss.getIndex(255,i));
	}
}
