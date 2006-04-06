import java.io.IOException;

import de.berlios.imilarity.image.ImageData;
import models.ImageCollection;

public class Kato implements ImageCollection {

private static final int PAGE_SIZE = 10;
	
	private static final String[] FILES = {
		"pic_0-0.png", "pic_0-1.png", "pic_0-2.png", "pic_0-3.png", "pic_0-4.png",
		"pic_0-5.png", "pic_1-0.png", "pic_1-1.png", "pic_1-2.png", "pic_1-3.png",
		"pic_1-4.png", "pic_2-0.png", "pic_2-1.png", "pic_3-0.png", "pic_3-1.png", 
		"pic_3-2.png", "pic_3-3.png", "pic_4-0.png", "pic_5-0.png", "pic_5-1.png", 
		"pic_5-2.png", "pic_5-3.png", "pic_5-4.png", "pic_5-5.png", "pic_5-6.png", 
		"pic_5-7.png", "pic_5-8.png", "pic_6-0.png", "pic_6-1.png", "pic_6-2.png"
	};
	
	private static final String DIR = "kato";

	
	private ImageData[] examples;
	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	public Kato() {
		try {
			examples = new ImageData[] { 
				ImageData.loadUrl(getClass().getResource(DIR + "/pic_0-0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/pic_1-0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/pic_3-0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/pic_5-0.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/pic_6-0.png"))
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
		String str1 = url1.substring(url1.lastIndexOf('_'),url1.lastIndexOf('-'));
		String str2 = url2.substring(url2.lastIndexOf('_'),url2.lastIndexOf('-'));
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
		return "Kato Subcollection";
	}

}
