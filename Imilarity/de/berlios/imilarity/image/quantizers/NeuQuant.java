package de.berlios.imilarity.image.quantizers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;


/* NeuQuant Neural-Net Quantization Algorithm
 * ------------------------------------------
 *
 * Copyright (c) 1994 Anthony Dekker
 *
 * NEUQUANT Neural-Net quantization algorithm by Anthony Dekker, 1994.
 * See "Kohonen neural networks for optimal colour quantization"
 * in "Network: Computation in Neural Systems" Vol. 5 (1994) pp 351-367.
 * for a discussion of the algorithm.
 * See also  http://www.acm.org/~dekker/NEUQUANT.HTML
 *
 * Any party obtaining a copy of these files from the author, directly or
 * indirectly, is granted, free of charge, a full and unrestricted irrevocable,
 * world-wide, paid up, royalty-free, nonexclusive right and license to deal
 * in this software and documentation files (the "Software"), including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons who receive
 * copies from any such party to do so, with the only requirement being
 * that this copyright notice remain intact.
 */

public class NeuQuant implements Quantizer {
	
	public static final int ncycles	=	100;			// no. of learning cycles
	
	public int netsize  = 8;		// number of colours used
	
	public static final int specials  = 3;		// number of reserved colours used
	public static final int bgColour  = specials-1;	// reserved background colour
	
	public int cutnetsize;
	public int maxnetpos;
	public int initrad;
	
	public static final int radiusbiasshift = 6;
	public static final int radiusbias = 1 << radiusbiasshift;
	
	public int initBiasRadius;
	
	public static final int radiusdec = 30; // factor of 1/30 each cycle
	
	public static final int alphabiasshift = 10;			// alpha starts at 1
	public static final int initalpha      = 1<<alphabiasshift; // biased by 10 bits
	
	public static final double gamma = 1024.0;
	public static final double beta = 1.0/1024.0;
	public static final double betagamma = beta * gamma;
	
	private double [] [] network; // the network itself
	protected int [] [] colormap; // the network itself
	
	private int [] netindex = new int [256]; // for network lookup - really 256
	
	private double [] bias;  // bias and freq arrays for learnin
	private double [] freq;
	
	// four primes near 500 - assume no image has a length so large
	// that it is divisible by all four primes
	
	public static final int prime1	=	499;
	public static final int prime2	=	491;
	public static final int prime3	=	487;
	public static final int prime4	=	503;
	public static final int maxprime=	prime4;
	
	protected int [] pixels = null;
	private int samplefac = 0;
	
	
	public NeuQuant(int sample, int numberOfColors) {
		if (sample < 1) throw new IllegalArgumentException ("Sample must be 1..30");
		if (sample > 30) throw new IllegalArgumentException ("Sample must be 1..30");
		samplefac = sample;
		netsize = numberOfColors;
		network = new double [netsize] [3]; // the network itself
		colormap = new int [netsize] [4]; // the network itself
		bias = new double [netsize];  // bias and freq arrays for learning
		freq = new double [netsize];
		cutnetsize  = netsize - specials;
		maxnetpos  = netsize-1;
		initrad	 = netsize/8;   // for 256 cols, radius starts at 32
		initBiasRadius = initrad*radiusbias;
		// rest later
	}
	
	public NeuQuant() {
		this(30, 8);
	}
	
	
	public void quantize(Image im) {
		setPixels(im);
		setUpArrays();
		init();
		int pc = im.getWidth() * im.getHeight();
		for (int i = 0; i < pc; i++)
			convert(i);
	}
	
	public int getBinsCount() {
		return netsize;
	}
	
	public Color getBinColor(int i) {
		if (i < 0 || i >= netsize) return null;
		int bb = colormap[i][0];
		int gg = colormap[i][1];
		int rr = colormap[i][2];
		return new Color(rr/255.0, gg/255.0, bb/255.0);
	}
	
	public int getBin(int i) {
		return lookup(pixels[i]);
	}
	
	public String getDescription() {
		return "NeuQuant(" + samplefac + ", " + netsize + ")";
	}	
	
	
	//// private methods:
	
	private void setUpArrays () {
		network [0] [0] = 0.0;	// black
		network [0] [1] = 0.0;
		network [0] [2] = 0.0;
		
		network [1] [0] = 1.0;	// white
		network [1] [1] = 1.0;
		network [1] [2] = 1.0;
		
		// RESERVED bgColour	// background
		
		for (int i=0; i<specials; i++) {
			freq[i] = 1.0 / netsize;
			bias[i] = 0.0;
		}
		
		for (int i=specials; i<netsize; i++) {
			double [] p = network [i];
			p[0] = (256.0 * (i-specials)) / cutnetsize;
			p[1] = (256.0 * (i-specials)) / cutnetsize;
			p[2] = (256.0 * (i-specials)) / cutnetsize;
			
			freq[i] = 1.0 / netsize;
			bias[i] = 0.0;
		}
	}    	
	
	private void setPixels(Image im) {
		if (im == null) throw new NullPointerException ("Image is null");
		int w = im.getWidth();
		int h = im.getHeight();
		setPixels (im, w, h);
	}
	
	private void setPixels(Image im, int w, int h) {
		if (w*h < maxprime) throw new IllegalArgumentException ("Image is too small");
		pixels = new int [w * h];
		for (int i = 0; i < pixels.length; i++) {
			double[] rgb = im.getColor(i).getComponents();
			pixels[i] = 0;
			pixels[i] |= ((int) (rgb[0]*255)) << 16;
			pixels[i] |= ((int) (rgb[1]*255)) << 8;
			pixels[i] |= ((int) (rgb[2]*255));
		}
	}
	
	
	private void init () {
		learn ();
		fix ();
		inxbuild ();
	}
	
	private void altersingle(double alpha, int i, double b, double g, double r) {
		// Move neuron i towards biased (b,g,r) by factor alpha
		double [] n = network[i];				// alter hit neuron
		n[0] -= (alpha*(n[0] - b));
		n[1] -= (alpha*(n[1] - g));
		n[2] -= (alpha*(n[2] - r));
	}
	
	private void alterneigh(double alpha, int rad, int i, double b, double g, double r) {
		
		int lo = i-rad;   if (lo<specials-1) lo=specials-1;
		int hi = i+rad;   if (hi>netsize) hi=netsize;
		
		int j = i+1;
		int k = i-1;
		int q = 0;
		while ((j<hi) || (k>lo)) {
			double a = (alpha * (rad*rad - q*q)) / (rad*rad);
			q ++;
			if (j<hi) {
				double [] p = network[j];
				p[0] -= (a*(p[0] - b));
				p[1] -= (a*(p[1] - g));
				p[2] -= (a*(p[2] - r));
				j++;
			}
			if (k>lo) {
				double [] p = network[k];
				p[0] -= (a*(p[0] - b));
				p[1] -= (a*(p[1] - g));
				p[2] -= (a*(p[2] - r));
				k--;
			}
		}
	}
	
	private int contest(double b, double g, double r) {    // Search for biased BGR values
		// finds closest neuron (min dist) and updates freq 
		// finds best neuron (min dist-bias) and returns position 
		// for frequently chosen neurons, freq[i] is high and bias[i] is negative 
		// bias[i] = gamma*((1/netsize)-freq[i]) 
		
		double bestd = Float.MAX_VALUE;
		double bestbiasd = bestd;
		int bestpos = -1;
		int bestbiaspos = bestpos;
		
		for (int i=specials; i<netsize; i++) {
			double [] n = network[i];
			double dist = n[0] - b;   if (dist<0) dist = -dist;
			double a = n[1] - g;   if (a<0) a = -a;
			dist += a;
			a = n[2] - r;   if (a<0) a = -a;
			dist += a;
			if (dist<bestd) {bestd=dist; bestpos=i;}
			double biasdist = dist - bias [i];
			if (biasdist<bestbiasd) {bestbiasd=biasdist; bestbiaspos=i;}
			freq [i] -= beta * freq [i];
			bias [i] += betagamma * freq [i];
		}
		freq[bestpos] += beta;
		bias[bestpos] -= betagamma;
		return bestbiaspos;
	}
	
	private int specialFind(double b, double g, double r) {
		for (int i=0; i<specials; i++) {
			double [] n = network[i];
			if (n[0] == b && n[1] == g && n[2] == r) return i;
		}
		return -1;
	}
	
	private void learn() {
		int biasRadius = initBiasRadius;
		int alphadec = 30 + ((samplefac-1)/3);
		int lengthcount = pixels.length;
		int samplepixels = lengthcount / samplefac;
		int delta = samplepixels / ncycles;
		int alpha = initalpha;
		
		int i = 0;
		int rad = biasRadius >> radiusbiasshift;
		if (rad <= 1) rad = 0;
		
		//System.err.println("beginning 1D learning: samplepixels=" + samplepixels + "  rad=" + rad);
		
		int step = 0;
		int pos = 0;
		
		if ((lengthcount%prime1) != 0) step = prime1;
		else {
			if ((lengthcount%prime2) !=0) step = prime2;
			else {
				if ((lengthcount%prime3) !=0) step = prime3;
				else step = prime4;
			}
		}
		
		i = 0;
		while (i < samplepixels) {
			int p = pixels [pos];
			int red   = (p >> 16) & 0xff;
			int green = (p >>  8) & 0xff;
			int blue  = (p      ) & 0xff;
			
			double b = blue;
			double g = green;
			double r = red;
			
			if (i == 0) {   // remember background colour
				network [bgColour] [0] = b;
				network [bgColour] [1] = g;
				network [bgColour] [2] = r;
			}
			
			int j = specialFind (b, g, r);
			j = j < 0 ? contest (b, g, r) : j;
			
			if (j >= specials) {   // don't learn for specials
				double a = (1.0 * alpha) / initalpha;
				altersingle (a, j, b, g, r);
				if (rad > 0) alterneigh (a, rad, j, b, g, r);   // alter neighbours
			}
			
			pos += step;
			while (pos >= lengthcount) pos -= lengthcount;
			
			i++;
			if (i%delta == 0) {	
				alpha -= alpha / alphadec;
				biasRadius -= biasRadius / radiusdec;
				rad = biasRadius >> radiusbiasshift;
				if (rad <= 1) rad = 0;
			}
		}
		//System.err.println("finished 1D learning: final alpha=" + (1.0 * alpha)/initalpha + "!");
	}
	
	private void fix() {
		for (int i=0; i<netsize; i++) {
			for (int j=0; j<3; j++) {
				int x = (int) (0.5 + network[i][j]);
				if (x < 0) x = 0;
				if (x > 255) x = 255;
				colormap[i][j] = x;
			}
			colormap[i][3] = i;
		}
	}
	
	private void inxbuild() {
		// Insertion sort of network and building of netindex[0..255]
		
		int previouscol = 0;
		int startpos = 0;
		
		for (int i=0; i<netsize; i++) {
			int[] p = colormap[i];
			int[] q = null;
			int smallpos = i;
			int smallval = p[1];			// index on g
			// find smallest in i..netsize-1
			for (int j=i+1; j<netsize; j++) {
				q = colormap[j];
				if (q[1] < smallval) {		// index on g
					smallpos = j;
					smallval = q[1];	// index on g
				}
			}
			q = colormap[smallpos];
			// swap p (i) and q (smallpos) entries
			if (i != smallpos) {
				int j = q[0];   q[0] = p[0];   p[0] = j;
				j = q[1];   q[1] = p[1];   p[1] = j;
				j = q[2];   q[2] = p[2];   p[2] = j;
				j = q[3];   q[3] = p[3];   p[3] = j;
			}
			// smallval entry is now in position i
			if (smallval != previouscol) {
				netindex[previouscol] = (startpos+i)>>1;
				for (int j=previouscol+1; j<smallval; j++) netindex[j] = i;
				previouscol = smallval;
				startpos = i;
			}
		}
		netindex[previouscol] = (startpos+maxnetpos)>>1;
		for (int j=previouscol+1; j<256; j++) netindex[j] = maxnetpos; // really 256
	}
	
	private int convert(int pixel) {
		int alfa = (pixel >> 24) & 0xff;
		int r = (pixel >> 16) & 0xff;
		int g = (pixel >>  8) & 0xff;
		int b = (pixel      ) & 0xff;
		int i = inxsearch(b, g, r);
		int bb = colormap[i][0];
		int gg = colormap[i][1];
		int rr = colormap[i][2];
		return (alfa << 24) | (rr << 16) | (gg << 8) | (bb);
	}
	
	private int lookup(int pixel) {
		int r = (pixel >> 16) & 0xff;
		int g = (pixel >>  8) & 0xff;
		int b = (pixel      ) & 0xff;
		int i = inxsearch(b, g, r);
		return i;
	}
	
//	private int lookup (int[] c) {
//		int i = inxsearch(c[2], c[1], c[0]);
//		return i;
//	}
//	
//	private int lookup (boolean rgb, int x, int g, int y) {
//		int i = rgb ? inxsearch (y, g, x) : inxsearch (x, g, y);
//		return i;
//	}
	
//	private int not_used_slow_inxsearch(int b, int g, int r) {
//		// Search for BGR values 0..255 and return colour index
//		int bestd = 1000;		// biggest possible dist is 256*3
//		int best = -1;
//		for (int i = 0; i<netsize; i++) {
//			int [] p = colormap[i];
//			int dist = p[1] - g;
//			if (dist<0) dist = -dist;
//			int a = p[0] - b;   if (a<0) a = -a;
//			dist += a;
//			a = p[2] - r;   if (a<0) a = -a;
//			dist += a;
//			if (dist<bestd) {bestd=dist; best=i;}
//		}
//		return best;
//	}
	
	private int inxsearch(int b, int g, int r) {
		// Search for BGR values 0..255 and return colour index
		int bestd = 1000;		// biggest possible dist is 256*3
		int best = -1;
		int i = netindex[g];	// index on g
		int j = i-1;		// start at netindex[g] and work outwards
		
		while ((i<netsize) || (j>=0)) {
			if (i<netsize) {
				int [] p = colormap[i];
				int dist = p[1] - g;		// inx key
				if (dist >= bestd) i = netsize;	// stop iter
				else {
					if (dist<0) dist = -dist;
					int a = p[0] - b;   if (a<0) a = -a;
					dist += a;
					if (dist<bestd) {
						a = p[2] - r;   if (a<0) a = -a;
						dist += a;
						if (dist<bestd) {bestd=dist; best=i;}
					}
					i++;
				}
			}
			if (j>=0) {
				int [] p = colormap[j];
				int dist = g - p[1]; // inx key - reverse dif
				if (dist >= bestd) j = -1; // stop iter
				else {
					if (dist<0) dist = -dist;
					int a = p[0] - b;   if (a<0) a = -a;
					dist += a;
					if (dist<bestd) {
						a = p[2] - r;   if (a<0) a = -a;
						dist += a;
						if (dist<bestd) {bestd=dist; best=j;}
					}
					j--;
				}
			}
		}
		
		return best;
	}
	
	
	
	public static void main(String[] args) {
		if (args.length != 2)
			System.out.println("usage: java NeuQuant <input image> <output image>");
		try {
			Image image = ImageData.loadFile(args[0]).getRgbImage();
			Quantizer quantizer = new NeuQuant(30, 8);
			long millis = System.currentTimeMillis();
			BufferedImage outImage = new BufferedImage(image.getWidth(), image.getHeight(), 
					BufferedImage.TYPE_INT_RGB);
			quantizer.quantize(image);
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					double[] rgb = 
						quantizer.getBinColor(quantizer.getBin(y*image.getWidth()+x)).getComponents();
					int pixel = 0;
					pixel |= (int)(rgb[0]*255) << 16;
					pixel |= (int)(rgb[1]*255) << 8;
					pixel |= (int)(rgb[2]*255);
					outImage.setRGB(x,y,pixel);
				}
			}
			System.out.println("Time: " + (System.currentTimeMillis()-millis));
			ImageIO.write(outImage,"png",new File(args[1]));
		} catch (IOException e) {
			System.err.println("IO Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
