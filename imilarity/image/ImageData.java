/*
 * Created on 25-sep-2005
 */
package image;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import image.RgbData;

/**
 * @author Klaas Bosteels
 */
public class ImageData {
	private final int id;
	private int page;
	
	private BufferedImage image;
	private String name;
	private URL url;
	
	private ImageData(int id, int page, BufferedImage image, String name, URL url) {
		this.id = id;
		this.page = page;
		this.image = image;
		this.name = name;
		this.url = url;
	}
	
	public ImageData(BufferedImage image, String name, URL url) {
		this(image.hashCode(), 1, image, name, url);
	}
	
	public int getId() { return id; }
	public int getPage() { return page; }
	public Image getImage() { return image; }
	public String getName() { return name; }
	public int getWidth() { return image.getWidth(); }
	public int getHeight() { return image.getHeight(); }
	public URL getUrl() { return url; }
	
	public void setPage(int page) { this.page = page; }
	public void setName(String name) { this.name = name; }
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
		return new ImageData(id, page, op.filter(image,null), name, url);
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
	
	
	public int getPixelCount() {
		return image.getWidth() * image.getHeight();
	}
	
	public RgbData getRgb(int x, int y) {
		int rgb = image.getRGB(x,y);
		int r = (rgb & 0x00ff0000) >> 16;
		int g = (rgb & 0x0000ff00) >> 8;
		int b = (rgb & 0x000000ff);
		return new RgbData(r, g, b);
	}
	
	public RgbData getRgb(int pixelNr) {
		int w = image.getWidth();
		return getRgb(pixelNr % w, pixelNr / w);
	}
	
	public int getCombinedRgb(int x, int y) {
		return image.getRGB(x,y);
	}
	
	public int getCombinedRgb(int pixelNr) {
		int w = image.getWidth();
		return getCombinedRgb(pixelNr % w, pixelNr / w);
	}
	
	public int getGrayscaleValue(int x, int y) {
		RgbData rgb = getRgb(x,y);
		return (int) (rgb.getR()*0.2125 + rgb.getG()*0.7154 + rgb.getB()*0.0721);
	}
	
	public int getGrayscaleValue(int pixelNr) {
		int w = image.getWidth();
		return getGrayscaleValue(pixelNr % w, pixelNr / w);
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
}
