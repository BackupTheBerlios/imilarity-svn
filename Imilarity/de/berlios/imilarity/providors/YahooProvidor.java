/*
 * Created on 25-sep-2005
 */
package de.berlios.imilarity.providors;


import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;



import com.yahoo.search.ImageSearchRequest;
import com.yahoo.search.ImageSearchResult;
import com.yahoo.search.ImageSearchResults;
import com.yahoo.search.SearchClient;
import com.yahoo.search.SearchException;

import de.berlios.imilarity.image.ImageData;

/**
 * @author Klaas Bosteels
 */
public class YahooProvidor extends ProvidorBase {
	
	private static final int PAGE_SIZE = 10;
	private static final int RESULTSET_SIZE = 100;
	
	private String keywords;
	
	
	public YahooProvidor(String keywords) {
		this.keywords = keywords;
	}
	
	
	/**
	 * @see de.berlios.imilarity.providors.Providor#getPageSize()
	 */
	public int getPageSize() {
		return PAGE_SIZE;
	}

	/**
	 * @see de.berlios.imilarity.providors.Providor#getPageCount()
	 */
	public int getPageCount() {
		return (RESULTSET_SIZE + PAGE_SIZE - 1) / PAGE_SIZE;
	}
	
	/**
	 * @see de.berlios.imilarity.providors.Providor#getDescription()
	 */
	public String getDescription() {
		return "Yahoo";
	}
	
	
	/**
	 * @see de.berlios.imilarity.providors.Providor#getPage(int) 
	 */
	public ImageData[] getPage(int page) throws IOException {
		SearchClient client = new SearchClient("imilarity");
		ImageSearchRequest request = new ImageSearchRequest(keywords);
        
		request.setResults(PAGE_SIZE);
		request.setStart(BigInteger.valueOf((PAGE_SIZE * (page-1)) + 1));
		
		ImageData[] collection = null;
        try {
            ImageSearchResults results = client.imageSearch(request);
                                                                                
            // Print out how many hits were found.
            //System.out.println("Found " + results.getTotalResultsAvailable() +
            //        " hits. Using the first " +
			//		results.getTotalResultsReturned() + ".");
                                                                                
            collection = new ImageData[results.listResults().length];
            for (int i = 0; 
            	i < results.listResults().length && 
				((page-1) * PAGE_SIZE) + i < RESULTSET_SIZE; 
            	i++) {
                
            	ImageSearchResult result = results.listResults()[i];
                try {
                	//collection[i] = ImageData.loadUrl(result.getUrl());
                	collection[i] = ImageData.loadUrl(result.getThumbnail().getUrl());
                	collection[i].setName(result.getTitle());
                	collection[i].setUrl(new URL(result.getUrl()));
                } catch(IOException e1) {
                	System.err.println("IOException: " + e1.getMessage());
                }
            }
        }
        catch (SearchException e) {
            // An issue with the XML or with the service.
            throw new IOException(e.getMessage());
        }
		return collection;
	}

}
