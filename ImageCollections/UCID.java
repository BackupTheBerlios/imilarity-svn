import java.io.IOException;

import de.berlios.imilarity.image.ImageData;
import models.ImageCollection;

public class UCID implements ImageCollection {
	
	private static final int PAGE_SIZE = 10;
	
	private static final String DIR = "ucid";
	
	private static final int[] RELEVANCE_SET = {
		0, 0, 0, 0, 0,  0, 0, 0, 1, 1,  // 100-109
		0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  // 110-119
		0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  // 120-129
		0, 0, 0, 0, 0,  0, 0, 0, 0, 0,  // 130-139
		3, 3, 3, 3, 2,  2, 2, 2, 2, 2,  // 140-149
		3, 0, 0, 0, 0,  4, 4, 0, 4, 0,  // 150-159
	};
	
	private ImageData[] examples;
	protected ImageData[][] pages;
	protected boolean[] pageLoaded;
	
	public UCID() {
		try {
			examples = new ImageData[] { 
				ImageData.loadUrl(getClass().getResource(DIR + "/pict0108.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/pict0145.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/pict0150.png")),
				ImageData.loadUrl(getClass().getResource(DIR + "/pict0156.png"))
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
		String str1 = url1.substring(url1.indexOf('0')+1,url1.indexOf('.'));
		String str2 = url2.substring(url2.indexOf('0')+1,url2.indexOf('.'));
		int i1 = Integer.parseInt(str1)-100, i2 = Integer.parseInt(str2)-100;
		return RELEVANCE_SET[i1] > 0 && RELEVANCE_SET[i2] > 0 &&
			RELEVANCE_SET[i1] == RELEVANCE_SET[i2];
	}

	public int getPageSize() {
		return PAGE_SIZE;
	}

	public int getPageCount() {
		return (RELEVANCE_SET.length + PAGE_SIZE - 1) / PAGE_SIZE;
	}

	public ImageData[] getPage(int page) throws IOException {
		if (!pageLoaded[page-1]) {
			pageLoaded[page-1] = true;
			pages[page-1] = new ImageData[PAGE_SIZE];
			int begin = ((page - 1) * PAGE_SIZE), end = begin + PAGE_SIZE;
			for (int i = begin; i < RELEVANCE_SET.length && i < end; i++) {
				pages[page-1][i-begin] =
					ImageData.loadUrl(getClass()
							.getResource(DIR+"/pict0"+(100+i)+".png"));
			}
		}
		// Als deze methode in twee aparte draden uitgevoerd wordt dan kan het 
		// voorkomen dat 'pages[x]' nog gelijk is aan 'null', terwijl de inhoud
		// van 'pages[x]' wel al berekend wordt. In dat geval moet er gewacht worden...
		else while (pages[page-1] == null) Thread.yield();
		return pages[page-1];
	}

	public String getDescription() {
		return "UCID Subcollection";
	}

}
