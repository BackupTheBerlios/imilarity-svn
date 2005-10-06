/*
 * Created on 23-sep-2005
 */
package de.berlios.imilarity.providors;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import de.berlios.imilarity.image.ImageData;




/**
 * @author Klaas Bosteels
 */
public class DirProvidor extends ProvidorBase {

	private final File[] files;
	
	private static final int PAGE_SIZE = 10;
	private int pageCount = 0;
	
	private static final FilenameFilter filter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			name = name.toLowerCase();
			return 
				name.endsWith(".jpg") ||
				name.endsWith(".jpeg") ||
				name.endsWith(".gif") ||
				name.endsWith(".png");
		}
	};
	
	
	public DirProvidor(String dir) {
		super();
		if (dir == null)
			throw new NullPointerException("dir == null");
		File dirFile = new File(dir);
		if (!dirFile.isDirectory())
			throw new IllegalArgumentException("not a directory");
		files = dirFile.listFiles(filter);
		pageCount = (files.length + PAGE_SIZE - 1) / PAGE_SIZE;
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
		return pageCount;
	}
	
	/**
	 * @see de.berlios.imilarity.providors.Providor#getDescription()
	 */
	public String getDescription() {
		return "Dir";
	}
	
	
	/**
	 * @see de.berlios.imilarity.providors.Providor#getPage(int)
	 */
	public ImageData[] getPage(int page) throws IOException {
		ImageData[] images = new ImageData[PAGE_SIZE];
		int begin = ((page - 1) * PAGE_SIZE), end = begin + PAGE_SIZE;
		for (int i = begin; i < files.length && i < end; i++) {
			if (!files[i].isDirectory()) {
				images[i-begin] = 
					ImageData.loadFile(files[i].getAbsolutePath()).getHScaledInstance(100);
			}
		}
		return images;
	}
}
