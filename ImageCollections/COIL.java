import java.io.IOException;

import models.ImageCollection;

import de.berlios.imilarity.image.ImageData;

public class COIL implements ImageCollection {

	private static final int PAGE_SIZE = 10;
	
	private static final String[] FILES = {
		"obj12__0.png",	"obj12__180.png", "obj12__270.png", "obj12__315.png",
		"obj12__45.png", "obj12__90.png", "obj16__0.png", "obj16__180.png",
		"obj16__270.png", "obj16__315.png", "obj16__45.png", "obj16__90.png",
		"obj3__0.png", "obj3__180.png", "obj3__270.png", "obj3__315.png",
		"obj3__45.png", "obj38__0.png",	"obj38__180.png", "obj38__270.png",
		"obj38__315.png", "obj38__45.png", "obj38__90.png", "obj3__90.png",
		"obj42__0.png", "obj42__180.png", "obj42__270.png", "obj42__315.png",
		"obj42__45.png", "obj42__90.png", "obj43__0.png", "obj43__180.png",
		"obj43__270.png", "obj43__315.png", "obj43__45.png", "obj43__90.png",
		"obj45__0.png",	"obj45__180.png", "obj45__270.png",	"obj45__315.png",
		"obj45__45.png", "obj45__90.png", "obj51__0.png", "obj51__180.png",
		"obj51__270.png", "obj51__315.png",	"obj51__45.png", "obj51__90.png",
		"obj59__0.png", "obj59__180.png", "obj59__270.png",	"obj59__315.png",
		"obj59__45.png", "obj59__90.png", "obj78__0.png", "obj78__180.png",
		"obj78__270.png", "obj78__315.png", "obj78__45.png", "obj78__90.png",
		"obj81__0.png", "obj81__180.png", "obj81__270.png", "obj81__315.png",
		"obj81__45.png", "obj81__90.png"
	};
	
	private static final String DIR = "coil";

	
	private ImageData[] examples;
	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	public COIL() {
		try {
			examples = new ImageData[] { 
				ImageData.loadUrl(getClass().getResource(DIR + "/obj3__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj12__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj16__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj38__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj42__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj43__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj45__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj51__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj59__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj78__0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/obj81__0.png"))
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		pages = new ImageData[getPageCount()][getPageSize()];
		for (int i = 0; i < pages.length; i++)
			pages[i] = null;
		pageLoaded = new boolean[getPageCount()];
	}
	
	
	public ImageData[] getExamples() {
		return examples;
	}
	
	public boolean areRelevant(ImageData im1, ImageData im2) {
		if (im1 == null || im2 == null) 
			throw new IllegalArgumentException("arguments must be != null");
		String url1 = im1.getUrl().toString();
		String url2 = im2.getUrl().toString();
		String str1 = url1.substring(url1.lastIndexOf('/'),url1.lastIndexOf('_'));
		String str2 = url2.substring(url2.lastIndexOf('/'),url2.lastIndexOf('_'));
		return str1.equals(str2);
	}

	
	public int getPageSize() {
		return PAGE_SIZE;
	}

	public int getPageCount() {
		return (FILES.length + PAGE_SIZE - 1) / PAGE_SIZE;
	}

	public ImageData[] getPage(int page) throws IOException {
		if (!pageLoaded[page-1]) {
			pageLoaded[page-1] = true;
			pages[page-1] = new ImageData[PAGE_SIZE];
			int begin = ((page - 1) * PAGE_SIZE), end = begin + PAGE_SIZE;
			for (int i = begin; i < FILES.length && i < end; i++) {
				pages[page-1][i-begin] =
					ImageData.loadUrl(getClass().getResource(DIR+"/"+FILES[i]));
			}
		}
		// Als deze methode in twee aparte draden uitgevoerd wordt dan kan het 
		// voorkomen dat 'pages[x]' nog gelijk is aan 'null', terwijl de inhoud
		// van 'pages[x]' wel al berekend wordt. In dat geval moet er gewacht worden...
		else while (pages[page-1] == null) Thread.yield();
		return pages[page-1];
	}

	public String getDescription() {
		return "COIL Subcollection";
	}

}
