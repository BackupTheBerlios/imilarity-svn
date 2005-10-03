/*
 * Created on 25-sep-2005
 */
package de.berlios.imilarity.image;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


/**
 * @author Klaas Bosteels
 */
public class ImageData implements Comparable {	
	private BufferedImage image;
	private String name;
	private double similarity = 0.0;
	private URL url;
	
	private ImageData(BufferedImage image, String name, URL url, double similarity) {
		this.image = image;
		this.name = name;
		this.url = url;
		this.similarity = similarity;
	}
	
	public ImageData(BufferedImage image, String name, URL url) {
		this(image, name, url, 0.0);
	}
	
	public Image getImage() { return image; }
	public String getName() { return name; }
	public double getSimilarity() { return similarity; }
	public int getWidth() { return image.getWidth(); }
	public int getHeight() { return image.getHeight(); }
	public URL getUrl() { return url; }
	
	public void setName(String name) { this.name = name; }
	public void setSimilarity(double similarity) { this.similarity = similarity; }
	public void setUrl(URL url) { this.url = url; }
	
	
	/**
	 * De teruggegeven instance heeft dezelfde ID als de oorspronkelijke instance.
	 */
	public ImageData getScaledInstance(double sx, double sy) {
		if (sx == 0.0 || sy == 0.0)
			return null;
		AffineTransform tx = new AffineTransform();
	    tx.setToScale(sx, sy);
	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return new ImageData(op.filter(image,null), name, url, similarity);
	}
	
	public ImageData getScaledInstance(int w, int h) {
		if (w == 0 || h == 0)
			return null;
	    return getScaledInstance(w * 1.0 / image.getWidth(), h * 1.0 / image.getHeight());
	}
	
	public ImageData getWScaledInstance(int w) {
		if (w == 0)
			return null;
		double scale = w * 1.0 / image.getWidth();
		return getScaledInstance(scale, scale);
	}
	
	public ImageData getHScaledInstance(int h) {
		if ( h == 0)
			return null;
		double scale = h * 1.0 / image.getHeight();
		return getScaledInstance(scale, scale);
	}
	
	
	public int[] getRgb(int x, int y) {
		int rgb = image.getRGB(x,y);
		int r = (rgb & 0x00ff0000) >> 16;
		int g = (rgb & 0x0000ff00) >> 8;
		int b = (rgb & 0x000000ff);
		return new int[] { r, g, b };
	}
	
	public int[] getRgb(int pixelNr) {
		int w = image.getWidth();
		return getRgb(pixelNr % w, pixelNr / w);
	}


	public ColorImage getColorImage() {
		return new ColorImageAdapter(this);
	}
	
	public GrayscaleImage getGrayscaleImage() {
		return new GrayscaleImageAdapter(getColorImage());
	}
	

	
	public static ImageData loadFile(String filename) throws IOException {
		File file = new File(filename); 
		return new ImageData(ImageIO.read(file), file.getName(), file.toURI().toURL());
	}
	
	public static ImageData loadUrl(URL url) throws IOException {
		return new ImageData(ImageIO.read(url), url.toString(), url);
	}
	
	public static ImageData loadUrl(String url) throws IOException {
		URL u = new URL(url);
		return new ImageData(ImageIO.read(u), url, u);
	}

	
	public int compareTo(Object arg0) {
		Double d1 = new Double(similarity);
		Double d2 = new Double(((ImageData)arg0).getSimilarity());
		return d2.compareTo(d1);
	}
}
