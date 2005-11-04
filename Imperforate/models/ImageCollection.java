package models;
import de.berlios.imilarity.image.ImageData;
import de.berlios.imilarity.providors.Providor;

public interface ImageCollection extends Providor {
	
	public ImageData[] getExamples();
	
	public boolean areRelevant(ImageData im1, ImageData im2);
	
}
