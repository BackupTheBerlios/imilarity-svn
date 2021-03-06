

/**
 * Master's Project step1
 * Color quantization using Wu's method
 * "Efficient Statistical Computations for Optimal Color Quantization"
 * in Graphics Gem Vol II, edited by James Arvo, AP.
 * pp 116-125
 * Based on C code available from:
 * <http://www.cis.ohio-state.edu/~parent/classes/781/graphics/GG2>
 *
 * @author Hak Kywn Roh, Klaas Bosteels
 */

package de.berlios.imilarity.image.quantizers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.berlios.imilarity.color.Color;
import de.berlios.imilarity.image.Image;
import de.berlios.imilarity.image.ImageData;


public class WuQuantizer implements Quantizer {
	private static final int MAXCOLOR = 256;
	private static final int RED = 2;
	private static final int GREEN = 1;
	private static final int BLUE = 0;
	
	private int size, K;
	private int qs = 33; //quant size
	private float m2[][][] = new float[qs][qs][qs];
	private int wt[][][];
	private int mr[][][];
	private int mg[][][];
	private int mb[][][];
	private int qadd[][];
	private short ir[][],ig[][],ib[][];
	private int height,width;
	
	private short lutr[];
	private short lutg[];
	private short lutb[];
	
	private int tag[][][];
	
	
	public WuQuantizer(int nK) {
		K = nK;
	}
	
	public void quantize(Image image) {
		height = image.getHeight();
		width = image.getWidth();
		size = width * height;
		
		m2 = new float[qs][qs][qs];
		wt = new int[qs][qs][qs];
		mr = new int[qs][qs][qs];
		mg = new int[qs][qs][qs];
		mb = new int[qs][qs][qs];
		
		lutr = new short[MAXCOLOR];
		lutg = new short[MAXCOLOR];
		lutb = new short[MAXCOLOR];
		
		ir = new short[width][height];
		ig = new short[width][height];
		ib = new short[width][height];
		
		for (int i = 0; i < size; i++) {
			int x = i % width;
			int y = i / width;
			double[] rgb = image.getColor(x, y).getComponents();
			ir[x][y] = (short) (rgb[0]*255);
			ig[x][y] = (short) (rgb[1]*255);
			ib[x][y] = (short) (rgb[2]*255);
		}
		
		int next, i, k, z;
		Box[] cube = new Box[MAXCOLOR];
		tag = new int[qs][qs][qs];
		float vv[] = new float[MAXCOLOR];
		float temp;
		long weight;
		/*
		 for (x=0;x<qs;x++)
		 for (y=0;y<qs;y++)
		 for (z=0;z<qs;z++)
		 {
		 wt[x][y][z]=
		 mr[x][y][z]=
		 mg[x][y][z]=
		 mb[x][y][z]=0;
		 m2[x][y][z]=(float)0.0;
		 }
		 */
		hist3d(wt, mr, mg, mb, m2);
		//System.out.println("Histogram done...");
		
		m3d(wt, mr, mg, mb, m2);
		//System.out.println("Moments done...");
		
		for (z = 0; z < MAXCOLOR; z++)
			cube[z] = new Box();
		
		cube[0].r0 = cube[0].g0 = cube[0].b0 = 0;
		cube[0].r1 = cube[0].g1 = cube[0].b1 = 32;
		
		next = 0;
		
		for (i = 1; i < K; i++) {
			if (cut(cube[next], cube[i]) != -1) {
				if (cube[next].vol > 1)
					vv[next] = var(cube[next]);
				else
					vv[next] = (float) 0.0;
				
				if (cube[i].vol > 1)
					vv[i] = var(cube[i]);
				else
					vv[i] = (float) 0.0;
			} else {
				vv[next] = (float) 0.0;
				i--;
			}
			next = 0;
			temp = vv[0];
			for (k = 1; k <= i; k++)
				if (vv[k] > temp) {
					temp = vv[k];
					next = k;
				}
			if (temp <= 0.0) {
				K = i + 1;
				System.out.println("Only got " + K + " boxes.");
				break;
			}
		}
		//System.out.println("Partition done...");
		
		
		for (k = 0; k < K; k++) {
			mark(cube[k], k, tag);
			
			weight = vol(cube[k], wt);
			if (weight != 0) {
				lutr[k] = (short) (vol(cube[k], mr) / weight);
				lutg[k] = (short) (vol(cube[k], mg) / weight);
				lutb[k] = (short) (vol(cube[k], mb) / weight);
			} else {
				//System.out.println("Bogus box " + k);
				lutr[k] = lutg[k] = lutb[k] = 0;
			}
		}
		
		
//		for (int y = 0; y < height; y++)
//			for (int x = 0; x < width; x++) {
//				int pr = qadd[x][y] / 1089;
//				int pt = qadd[x][y] % 1089;
//				int pg = pt / 33;
//				int pb = pt % 33;
//				
//				qadd[x][y] = tag[pr][pg][pb];
//			}
	}
	
	public int getBinsCount() {
		//return MAXCOLOR;
		return K;
	}
	
	public Color getBinColor(int i) {
		if (i < 0 || i >= MAXCOLOR) return null;
		return new Color(lutr[i]/255.0, lutg[i]/255.0, lutb[i]/255.0);
	}
	
	
	public int getBin(int i) {
		int x = i % width;
		int y = i / width;
		int pr = qadd[x][y] / 1089;
		int pt = qadd[x][y] % 1089;
		int pg = pt / 33;
		int pb = pt % 33;
		return tag[pr][pg][pb];
	} 
	
	
	public String getDescription() {
		return "Wu(" + K + ")";
	}
	
	//// private methoden 
	
	private void hist3d(
			int vwt[][][],
			int vmr[][][],
			int vmg[][][],
			int vmb[][][],
			float vm2[][][]) {
		int i,inr,ing,inb;
		int table[] = new int[MAXCOLOR];
		short tr,tg,tb;
		
		for (i = 0; i < MAXCOLOR; i++)
			table[i] = i * i;
		
		qadd = new int[width][height];
		
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				tr = ir[x][y];
				tg = ig[x][y];
				tb = ib[x][y];
				inr = (tr >> 3) + 1;
				ing = (tg >> 3) + 1;
				inb = (tb >> 3) + 1;
				qadd[x][y] = ((inr << 10) + (inr << 6) + inr + (ing << 5) + ing + inb);
				vwt[inr][ing][inb]++;
				vmr[inr][ing][inb] += tr;
				vmg[inr][ing][inb] += tg;
				vmb[inr][ing][inb] += tb;
				vm2[inr][ing][inb] += (float) (table[tr] + table[tg] + table[tb]);
			}
	}
	
	private void m3d(
			int vwt[][][],
			int vmr[][][],
			int vmg[][][],
			int vmb[][][],
			float vm2[][][]) {
		short i, tr, tg, tb;
		int line, liner, lineg,lineb;
		int area[] = new int[33];
		int arear[] = new int[33];
		int areag[] = new int[33];
		int areab[] = new int[33];
		float line2;
		float area2[] = new float[33];
		
		for (tr = 1; tr <= 32; tr++) {
			for (i = 0; i <= 32; i++)
				area2[i] = area[i] = arear[i] = areag[i] = areab[i] = 0;
			for (tg = 1; tg <= 32; tg++) {
				line2 = line = liner = lineg = lineb = 0;
				for (tb = 1; tb <= 32; tb++) {
					line += vwt[tr][tg][tb];
					liner += vmr[tr][tg][tb];
					lineg += vmg[tr][tg][tb];
					lineb += vmb[tr][tg][tb];
					line2 += vm2[tr][tg][tb];
					
					area[tb] += line;
					arear[tb] += liner;
					areag[tb] += lineg;
					areab[tb] += lineb;
					area2[tb] += line2;
					
					vwt[tr][tg][tb] = vwt[tr - 1][tg][tb] + area[tb];
					vmr[tr][tg][tb] = vmr[tr - 1][tg][tb] + arear[tb];
					vmg[tr][tg][tb] = vmg[tr - 1][tg][tb] + areag[tb];
					vmb[tr][tg][tb] = vmb[tr - 1][tg][tb] + areab[tb];
					vm2[tr][tg][tb] = vm2[tr - 1][tg][tb] + area2[tb];
					
				}
			}
		}
		
	}
	
	private long vol(Box cube, int mmt[][][]) {
		return 
			mmt[cube.r1][cube.g1][cube.b1] - 
			mmt[cube.r1][cube.g1][cube.b0] - 
			mmt[cube.r1][cube.g0][cube.b1] + 
			mmt[cube.r1][cube.g0][cube.b0] - 
			mmt[cube.r0][cube.g1][cube.b1] + 
			mmt[cube.r0][cube.g1][cube.b0] + 
			mmt[cube.r0][cube.g0][cube.b1] - 
			mmt[cube.r0][cube.g0][cube.b0];
	}
	
	private long bottom(Box cube, int dir, int mmt[][][]) {
		switch (dir) {
		case RED:
			return 
				-mmt[cube.r0][cube.g1][cube.b1] +
				mmt[cube.r0][cube.g1][cube.b0] + 
				mmt[cube.r0][cube.g0][cube.b1] - 
				mmt[cube.r0][cube.g0][cube.b0];
		case GREEN:
			return 
				-mmt[cube.r1][cube.g0][cube.b1] +
				mmt[cube.r1][cube.g0][cube.b0] + 
				mmt[cube.r0][cube.g0][cube.b1] - 
				mmt[cube.r0][cube.g0][cube.b0];
		case BLUE:
			return 
				-mmt[cube.r1][cube.g1][cube.b0] +
				mmt[cube.r1][cube.g0][cube.b0] + 
				mmt[cube.r0][cube.g1][cube.b0] - 
				mmt[cube.r0][cube.g0][cube.b0];
		}
		
		return (long) 1;
	}
	
	private long top(Box cube, int dir, int pos,
			int mmt[][][]) {
		switch (dir) {
		case RED:
			return (mmt[pos][cube.g1][cube.b1] - 
					mmt[pos][cube.g1][cube.b0] - 
					mmt[pos][cube.g0][cube.b1] + 
					mmt[pos][cube.g0][cube.b0]);
		case GREEN:
			return (mmt[cube.r1][pos][cube.b1] - 
					mmt[cube.r1][pos][cube.b0] - 
					mmt[cube.r0][pos][cube.b1] + 
					mmt[cube.r0][pos][cube.b0]);
		case BLUE:
			return (mmt[cube.r1][cube.g1][pos] - 
					mmt[cube.r1][cube.g0][pos] - 
					mmt[cube.r0][cube.g1][pos] + 
					mmt[cube.r0][cube.g0][pos]);
		}
		
		return (long) 1;
	}
	
	private float var(Box cube) {
		float dr, dg, db, xx;
		
		dr = vol(cube, mr);
		dg = vol(cube, mg);
		db = vol(cube, mb);
		xx = 
			m2[cube.r1][cube.g1][cube.b1] - 
			m2[cube.r1][cube.g1][cube.b0] - 
			m2[cube.r1][cube.g0][cube.b1] +
			m2[cube.r1][cube.g0][cube.b0] - 
			m2[cube.r0][cube.g1][cube.b1] + 
			m2[cube.r0][cube.g1][cube.b0] + 
			m2[cube.r0][cube.g0][cube.b1] - 
			m2[cube.r0][cube.g0][cube.b0];
		
		return (xx - (dr * dr + dg * dg + db * db) / (float) vol(cube, wt));
	}
	
	private float maximize(Box cube, int dir, int first, int last,
			int cut[],
			long wholer,
			long wholeg,
			long wholeb,
			long wholew) {
		long halfr, halfg, halfb, halfw;
		long baser, baseg, baseb, basew;
		int i;
		float temp, max;
		
		baser = bottom(cube, dir, mr);
		baseg = bottom(cube, dir, mg);
		baseb = bottom(cube, dir, mb);
		basew = bottom(cube, dir, wt);
		max = (float) 0.0;
		cut[0] = -1;
		
		for (i = first; i < last; i++) {
			halfr = baser + top(cube, dir, i, mr);
			halfg = baseg + top(cube, dir, i, mg);
			halfb = baseb + top(cube, dir, i, mb);
			halfw = basew + top(cube, dir, i, wt);
			
			if (halfw == 0)
				continue;
			else
				temp = ((float) halfr * halfr + (float) halfg * halfg +
						(float) halfb * halfb) / halfw;
			
			halfr = wholer - halfr;
			halfg = wholeg - halfg;
			halfb = wholeb - halfb;
			halfw = wholew - halfw;
			
			if (halfw == 0)
				continue;
			else
				temp += ((float) halfr * halfr + (float) halfg * halfg +
						(float) halfb * halfb) / halfw;
			
			if (temp > max) {
				max = temp;
				cut[0] = i;
			}
		}
		
		return (max);
	}
	
	private int cut(Box set1, Box set2) {
		int dir;
		int cutr[] = new int[1];
		int cutg[] = new int[1];
		int cutb[] = new int[1];
		float maxr, maxg, maxb;
		long wholer, wholeg, wholeb, wholew;
		
		wholer = vol(set1, mr);
		wholeg = vol(set1, mg);
		wholeb = vol(set1, mb);
		wholew = vol(set1, wt);
		
		
		maxr = maximize(set1, RED, set1.r0 + 1, set1.r1, cutr,
				wholer, wholeg, wholeb, wholew);
		maxg = maximize(set1, GREEN, set1.g0 + 1, set1.g1, cutg,
				wholer, wholeg, wholeb, wholew);
		maxb = maximize(set1, BLUE, set1.b0 + 1, set1.b1, cutb,
				wholer, wholeg, wholeb, wholew);
		if ((maxr >= maxg) && (maxr >= maxg)) {
			dir = RED;
			if (cutr[0] < 0)
				return 0;
		} else if ((maxg >= maxr) && (maxg >= maxb)) {
			dir = GREEN;
		} else
			dir = BLUE;
		
		
		set2.r1 = set1.r1;
		set2.g1 = set1.g1;
		set2.b1 = set1.b1;
		
		switch (dir) {
		case RED:
			set2.r0 = set1.r1 = cutr[0];
			set2.g0 = set1.g0;
			set2.b0 = set1.b0;
			break;
		case GREEN:
			set2.r0 = set1.r0;
			set2.g0 = set1.g1 = cutg[0];
			set2.b0 = set1.b0;
			break;
		case BLUE:
			set2.r0 = set1.r0;
			set2.g0 = set1.g0;
			set2.b0 = set1.b1 = cutb[0];
			break;
		}
		
		set1.vol = (set1.r1 - set1.r0) * (set1.g1 - set1.g0) * (set1.b1 - set1.b0);
		set2.vol = (set2.r1 - set2.r0) * (set2.g1 - set2.g0) * (set2.b1 - set2.b0);
		return 1;
	}
	
	private void mark(Box cube, int label, int tag[][][]) {
		int tr, tg, tb;
		
		for (tr = cube.r0 + 1; tr <= cube.r1; tr++)
			for (tg = cube.g0 + 1; tg <= cube.g1; tg++)
				for (tb = cube.b0 + 1; tb <= cube.b1; tb++)
					tag[tr][tg][tb] = label;
	}
	
	
	
	private static class Box {
		public int r0;	 /* min value, exclusive */
		public int r1;	 /* max value, inclusive */
		public int g0;
		public int g1;
		public int b0;
		public int b1;
		public int vol;
	}
	
	
	
	// TESTPROGRAMMA
	
	public static void main(String[] args) {
		if (args.length != 2)
			System.out.println("usage: java Wu <input image> <output image>");
		try {
			Image image = ImageData.loadFile(args[0]).getRgbImage();
			Quantizer quantizer = new WuQuantizer(8);
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
