
public class Remainder {

	private static int g(int k) {
		if (k > 16)
			return 17+((k-17)%240);
		else
			return 256+((k-16)%240);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("remainder: " + ((-361) % 360));
		
		for (int k = -300; k <= 300; k++)
			System.out.println(""+g(k));
	}

}
