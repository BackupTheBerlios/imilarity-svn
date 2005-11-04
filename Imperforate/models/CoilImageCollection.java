package models;

import java.io.IOException;

import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.providors.DirProvidor;
import de.berlios.imilarity.providors.Providor;

public class CoilImageCollection implements ImageCollection {

	// TODO: dir verhuizen
	private static final String DIR = "/home/klbostee/Images/coil5";

	private static ImageData[] EXAMPLES;
	static {
		try {
			EXAMPLES = new ImageData[] { 
					ImageData.loadFile(DIR + "/obj3__0.png"),
					ImageData.loadFile(DIR + "/obj12__0.png"),
					ImageData.loadFile(DIR + "/obj16__0.png"),
					ImageData.loadFile(DIR + "/obj38__0.png"),
					ImageData.loadFile(DIR + "/obj42__0.png"),
					ImageData.loadFile(DIR + "/obj43__0.png"),
					ImageData.loadFile(DIR + "/obj45__0.png"),
					ImageData.loadFile(DIR + "/obj51__0.png"),
					ImageData.loadFile(DIR + "/obj59__0.png"),
					ImageData.loadFile(DIR + "/obj78__0.png"),
					ImageData.loadFile(DIR + "/obj81__0.png")
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	
	private Providor providor = new DirProvidor(DIR); 
	
	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	public CoilImageCollection() {
		pages = new ImageData[getPageCount()][getPageSize()];
		for (int i = 0; i < pages.length; i++)
			pages[i] = null;
		pageLoaded = new boolean[getPageCount()];
	}
	
	
	public ImageData[] getExamples() {
		return EXAMPLES;
	}
	
	public boolean areRelevant(ImageData im1, ImageData im2) {
		if (im1 == null || im2 == null) 
			throw new IllegalArgumentException("arguments must be != null");
		String url1 = im1.getUrl().toString();
		String url2 = im2.getUrl().toString();
		String str1 = url1.substring(url1.lastIndexOf('/'),url1.indexOf('_'));
		String str2 = url2.substring(url2.lastIndexOf('/'),url2.indexOf('_'));
		return str1.equals(str2);
	}

	
	public int getPageSize() {
		return providor.getPageSize();
	}

	public int getPageCount() {
		return providor.getPageCount();
	}

	public ImageData[] getPage(int page) throws IOException {
		if (!pageLoaded[page-1]) {
			pageLoaded[page-1] = true;
			pages[page-1] = providor.getPage(page);
		}
		// Als deze methode in twee aparte draden uitgevoerd wordt dan kan het 
		// voorkomen dat 'pages[x]' nog gelijk is aan 'null', terwijl de inhoud
		// van 'pages[x]' wel al berekend wordt. In dat geval moet er gewacht worden...
		else while (pages[page-1] == null) Thread.yield();
		return pages[page-1];
	}

	public String getDescription() {
		return "CoilCollection";
	}

	
	
}
