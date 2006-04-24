package models;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.providers.Provider;

public interface ImageCollection extends Provider {
	
	public ImageData[] getExamples();
	
	public boolean areRelevant(ImageData im1, ImageData im2);
	
}
