/*
 * Created on 25-sep-2005
 */
package de.berlios.imilarity.providers;


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
public class YahooProvider extends ProviderBase {
	
	private static final int PAGE_SIZE = 5;
	private static final int RESULTSET_SIZE = 100;
	
	private String keywords;
	
	
	public YahooProvider(String keywords) {
		this.keywords = keywords;
	}
	
	
	/**
	 * @see de.berlios.imilarity.providers.Provider#getPageSize()
	 */
	public int getPageSize() {
		return PAGE_SIZE;
	}

	/**
	 * @see de.berlios.imilarity.providers.Provider#getPageCount()
	 */
	public int getPageCount() {
		return (RESULTSET_SIZE + PAGE_SIZE - 1) / PAGE_SIZE;
	}
	
	/**
	 * @see de.berlios.imilarity.providers.Provider#getDescription()
	 */
	public String getDescription() {
		return "Yahoo";
	}
	
	
	/**
	 * @see de.berlios.imilarity.providers.Provider#getPage(int) 
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
                                                                                
            collection = new ImageData[PAGE_SIZE];
            int i = 0;
            while(i < results.listResults().length && 
				((page-1) * PAGE_SIZE) + i < RESULTSET_SIZE) {
                
            	ImageSearchResult result = results.listResults()[i];
                try {
                	//collection[i] = ImageData.loadUrl(result.getUrl());
                	collection[i] = ImageData.loadUrl(result.getThumbnail().getUrl());
                	collection[i].setName(result.getTitle());
                	collection[i].setUrl(new URL(result.getUrl()));
                } catch(IOException e1) {
                	System.err.println("IOException: " + e1.getMessage());
                }
                i++;
            }
            while (i < PAGE_SIZE) { // aanvullen met null'en indien nodig
            	collection[i] = null;
            	i++;
            }
        }
        catch (SearchException e) {
            // An issue with the XML or with the service.
            throw new IOException(e.getMessage());
        }
		return collection;
	}

}
