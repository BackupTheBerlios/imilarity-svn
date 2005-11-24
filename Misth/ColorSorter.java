
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import de.berlios.imilarity.fuzzy.ColorMembership;
import de.berlios.imilarity.image.Color;

public class ColorSorter {

	private SortedSet colorsSet = new TreeSet();
	
	public ColorSorter(Color[] colors) {
		for (int i = 0; i < colors.length; i++)
			colorsSet.add(new CompColor(colors[i]));
	}
	
	public Color[] getSortedColors() {
		Color[] result = new Color[colorsSet.size()];
		Iterator it = colorsSet.iterator();
		for (int i = 0; it.hasNext(); i++)
			result[i] = (Color) it.next();
		return result;
	}
	
	private static class CompColor extends Color implements Comparable {

		public CompColor(double[] components) {
			super(components);
		}

		public CompColor(Color color) {
			super(color.getComponents());
		}
		
		public int compareTo(Object arg0) {
			ColorMembership cm1 = new ColorMembership(this);
			ColorMembership cm2 = new ColorMembership((Color)arg0);
			return cm1.compareTo(cm2);
		}
		
	}
	
	
	
	private static Color[] createColors(int[][] values) {
		Color[] result = new Color[values.length];
		for (int i = 0; i < result.length; i++)
			result[i] = new Color(values[i][0]*1.0/255,values[i][1]*1.0/255,values[i][2]*1.0/255);
		return result;
	}
	
	private static void printColors(ColorSorter sorter) {
		Color[] colors = sorter.getSortedColors();
		for (int i = 0; i < colors.length; i++) {
			double[] comps = colors[i].getComponents();
			System.out.println("" + (i+1) + ": " 
						+ (comps[0]*255) + ", " 
						+ (comps[1]*255) + ", "
						+ (comps[2]*255));
		}
	}
	
	public static void main(String[] args) {
		int[][] colors1 = {
			new int[] {255,255,255},
			new int[] {127,127,127},
			new int[] {255,0,0},
			new int[] {160,32,240},
			new int[] {0,0,255},
			new int[] {173,216,230},
			new int[] {0,255,0},
			new int[] {255,255,0},
			new int[] {255,165,0}
		};
		int[][] colors2 = {
			new int[] {165,42,42},
			new int[] {139,105,20},
			new int[] {30,144,255},
			new int[] {255,192,203},
			new int[] {144,238,144},
			new int[] {211,38,219},
			new int[] {23,134,129},
			new int[] {191,191,191},
			new int[] {227,226,41}
		};
		int[][] colors3 = {
				new int[] {160,  32, 240},
				new int[] {255, 192, 203},
				new int[] {255, 128,   0},
				new int[] {255,   0,   0},
				new int[] {128,  42,  42},
				new int[] {255, 255,   0},
				new int[] {  0, 255,   0},
				new int[] {  0,   0, 255},
				new int[] {  0,	  0,   0},
				new int[] {255, 255, 255},
				new int[] {192, 192, 192}
			};
		
		System.out.println("Eerste reeks:");
		printColors(new ColorSorter(createColors(colors1)));
		
		System.out.println("Tweede reeks:");
		printColors(new ColorSorter(createColors(colors2)));
		
		System.out.println("Focal reeks:");
		printColors(new ColorSorter(createColors(colors3)));
	}
	
}
