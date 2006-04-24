package models;

import java.io.IOException;

import de.berlios.imilarity.Imilarity;
import de.berlios.imilarity.aggregators.*;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.measures.*;
import de.berlios.imilarity.providers.Provider;

public class EvalImilarity extends Imilarity implements Comparable {
	
	private ImageCollection collection;
	
	private ImageMeasure measure;
	private Aggregator aggregator;
	
	private long cpuTime = 0;
	private double nar = 0.0;
	private double[] firstSimilarities;
	private String[] firstUrls;
	
	
	public EvalImilarity(ImageCollection collection) {
		if (collection == null)
			throw new NullPointerException("collection == null");
		this.collection = collection;
		super.setProvider(collection);
	}
	
	public void setMeasure(ImageMeasure measure) {
		super.setMeasure(measure);
		this.measure = measure;
	}
	
	public void setAggregator(Aggregator aggregator) {
		super.setAggregator(aggregator);
		this.aggregator = aggregator;
	}
	
	public void setProvider(Provider provider) {
		throw new UnsupportedOperationException("the collection is the provider for this object");
	}
	
	public void reorderPage(int page) {
		long millis = System.currentTimeMillis();
		super.reorderPage(page);
		cpuTime += (System.currentTimeMillis() - millis);
	}

	public void mergeReorderedPages() {
		long millis = System.currentTimeMillis();
		super.mergeReorderedPages();
		cpuTime += (System.currentTimeMillis() - millis);
		
		try {
			int rank = 0, relevantCount = 0;
			int sum = 0;
			for (int i = 1; i <= getPageCount(); i++) {
				ImageData[] page = getPage(i);
				for (int j = 0; j < page.length; j++) {
					rank++;
					if (page[j] != null && isRelevant(page[j])) {
						sum += rank;
						relevantCount++;
					}
				}
			}
			nar = (sum - (relevantCount*(relevantCount+1)/2)) * 1.0 / (rank * relevantCount);
			
			firstSimilarities = new double[getPageSize()];
			firstUrls = new String[getPageSize()];
			ImageData[] page = getPage(1);
			for (int i = 0; i < getPageSize(); i++) {
				firstSimilarities[i] = page[i].getSimilarity();
				firstUrls[i] = page[i].getUrl().toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isRelevant(ImageData image) {
		ImageData[] examples = getExamples();
		for (int i = 0; i < examples.length; i++)
			if (collection.areRelevant(examples[i], image))
				return true;
		return false;
	}
	
	public long getCpuTime() {
		return cpuTime;
	}
	
	public double getNar() {
		return nar;
	}
	
	public double[] getFirstSimilarities() {
		return firstSimilarities;
	}
	
	public String[] getFirstUrls() {
		return firstUrls;
	}
	
	public void clearFields() {
		cpuTime = 0;
		nar = 0;
		firstSimilarities = null;
		firstUrls = null;
	}

	
	public String getDescription() {
		return "Measure: " + measure.getDescription() + ", Aggregator: " + aggregator;
	}
	
	
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof EvalImilarity))
			throw new IllegalArgumentException("must be an instance of Evaluator");
		return new Double(nar).compareTo(new Double(((EvalImilarity)arg0).nar));
	}
	
}
