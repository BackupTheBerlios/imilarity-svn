/*
 * Created on 25-sep-2005
 */
package gui;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

import de.berlios.imilarity.image.ImageData;

import models.ExamplesModel;
import models.ImageModel;
import models.ProgressModel;
import models.SearchModel;


/**
 * @author Klaas Bosteels
 */
public class ResultsPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 6671213565105241810L;

	private SearchModel searchModel;
	private ImageModel selectedImgModel, fullSizeImgModel;
	private ProgressModel progressModel;
	
	private JProgressBar progressBar, fsProgressBar;
	private JPanel resultsPanel, centerPanel, sidebar;
	private ImagePanel fullSizePanel;
	private CardLayout cardLayout;
	
	
	public ResultsPanel(SearchModel searchModel, ExamplesModel examplesModel,
			ProgressModel progressModel, ImageModel selectedImgModel) {
		super();
		
		if (searchModel == null)
			throw new NullPointerException("searchModel == null");
		this.searchModel = searchModel;
		searchModel.addObserver(this);
		
		if (examplesModel == null)
			throw new NullPointerException("searchModel == null");
		
		if (progressModel == null)
			throw new NullPointerException("progressModel == null");
		this.progressModel = progressModel;
		
		if (selectedImgModel == null)
			throw new NullPointerException("selectedImgModel == null");
		this.selectedImgModel = selectedImgModel;
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		FlowLayout fl = new ScrollFlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		resultsPanel = new ScrollPanel();
		resultsPanel.setLayout(fl);
		resultsPanel.setOpaque(false);
		resultsPanel.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));
		
		JScrollPane resultsScrollPane = new JScrollPane(resultsPanel);
		resultsScrollPane.setBorder(null);
		resultsScrollPane.getViewport().setBackground(Color.WHITE);
	
		fullSizePanel = new ImagePanel();
		fullSizePanel.setBackground(Color.WHITE);
		fullSizePanel.setLayout(new BoxLayout(fullSizePanel, BoxLayout.Y_AXIS));
		fullSizePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fullSizePanel.setToolTipText("Click here to go back");
		fullSizePanel.add(Box.createGlue());
		fsProgressBar = new JProgressBar();
		fsProgressBar.setIndeterminate(true);
		fsProgressBar.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		fsProgressBar.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		fsProgressBar.setString("loading image ...");
		fsProgressBar.setStringPainted(true);
		fsProgressBar.setMaximumSize
			(new Dimension(300,fsProgressBar.getPreferredSize().height));
		fsProgressBar.setVisible(false);
		fullSizePanel.add(fsProgressBar);
		fullSizePanel.add(Box.createGlue());
		
		cardLayout = new CardLayout();
		centerPanel = new JPanel(cardLayout);
		centerPanel.add(resultsScrollPane, "results");
		centerPanel.add(fullSizePanel, "fullSize");
		add(centerPanel);
		
		fullSizePanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(centerPanel, "results");
			}
		});
		
		progressBar = new ProgressBar(progressModel);
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		add(progressBar, BorderLayout.SOUTH);
		
		fullSizeImgModel = new ImageModel();
		fullSizeImgModel.addObserver(this);
		sidebar = new SideBar(selectedImgModel, fullSizeImgModel, examplesModel);
		add(sidebar, BorderLayout.WEST);
	}
	
	public void setCursor(Cursor cursor) {
		super.setCursor(cursor);
		sidebar.setCursor(cursor);
	}
	
	
	private void addImages(ImageData[] images) {
		for (int i = 0; i < images.length; i++) {
			if (images[i] != null) {
				JLabel label = new ThumbLabel(images[i]);
				//button.setToolTipText(images[i].getName());
				label.setPreferredSize(new Dimension(100,100));
				label.setBackground(Color.WHITE);
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
				resultsPanel.add(label);
			}
		}
		revalidate();
		repaint();
	}
	
	private boolean busy1 = false, busy2 = false;
	
	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (o == searchModel && !busy1) {
			busy1 = true;
			resultsPanel.removeAll();
			progressModel.setValue(0);
			progressModel.setMin(0);
			progressModel.setMax(searchModel.getPageCount());
			progressModel.setVisible(true);
			new Thread(new Runnable() {
				public void run() {
					try {
						for (int i = 1; i <= searchModel.getPageCount(); i++) {
							final ImageData[] thumbs = searchModel.getPage(i);
							final int page = i;
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									ResultsPanel.this.addImages(thumbs);
									progressModel.setValue(page);
								}
							});
						}
					} catch (final IOException e) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								JOptionPane.showMessageDialog(ResultsPanel.this,
										e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
						});
					} finally  {
						ResultsPanel.this.busy1 = false;
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								progressModel.setVisible(false);	
								revalidate();
								repaint();
							}
						});
					}
				}
			}).start();
		}
		else if (o == fullSizeImgModel && ! busy2) {
			busy2 = true;
			fullSizePanel.setImage(null);
			fsProgressBar.setVisible(true);
			cardLayout.show(centerPanel, "fullSize");
			new Thread(new Runnable() {
				public void run() {
					try {
						URL url = fullSizeImgModel.getImageData().getUrl();
						final ImageData id = ImageData.loadUrl(url);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								fullSizePanel.setImage(id);
							}
						});
					} catch (IOException e) {
						// TODO: dialog
						System.err.println("ERROR: Cannot load requested image!");
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								cardLayout.show(centerPanel, "results");
							}
						});
					} finally {
						ResultsPanel.this.busy2 = false;
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								fsProgressBar.setVisible(false);
							}
						});
					}
				}
			}).start();
		}
	}
	
	
	
	private class ThumbLabel extends JLabel implements MouseListener {
		
		private static final long serialVersionUID = 65727315412665718L;
		
		private ImageData imageData;
		private boolean mouseOver = false;
		
		public ThumbLabel(ImageData id) {
			super();
			this.setHorizontalAlignment(JLabel.CENTER);
			imageData = id;
			if (id.getWidth() >= id.getHeight())
				id = id.getWScaledInstance(90);
			else
				id = id.getHScaledInstance(90);
			setIcon(new ShadowIcon(id));
			this.addMouseListener(this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (mouseOver) {
				g.setColor(new Color(255,255,255,100));
				g.fillRect(0,0,getWidth(), getHeight());
			}
		}
		
		public void mouseClicked(MouseEvent e) {
			selectedImgModel.setImageData(imageData);	
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e) { 
			mouseOver = true;
			repaint();
		}
		public void mouseExited(MouseEvent e) { 
			mouseOver = false;
			repaint();
		}		
	}
	
	/*
	 * FlowLayout is too dumb a LayoutManager for use in containers that need to
	 * be placed in a JScrollPane (unless you want only horizontal scrolling).
	 * This is because the preferredLayoutSize value returned by it is always
	 * the same - irrespective of the actual width of the container. (And
	 * therefore the preferredSize value returned by the panel would always be
	 * what is required to place all components in a single row).
	 * 
	 * Setting the preferredSize value to a hard coded value number is no
	 * solution either, because the panel will now always return the set value
	 * irrespective of the components it contains.
	 * 
	 * ScrollFlowLayout is a modified FlowLayout class that will return a variable
	 * preferredSize depending on the current width of the container, and hence
	 * allow you to enable vertical scrolling.
	 * 
	 * Also, in order to limit your panel width and enable wrapping, it is
	 * better to make your panel implement the Scrollable interface. Return true
	 * for the getScrollableTracksViewportWidth() method so that horizontal
	 * scrolling is disabled. ScrollPanel does that. 
	 */
	
	private class ScrollPanel extends JPanel implements Scrollable {

		private static final long serialVersionUID = 615123477779088908L;

		public Dimension getPreferredScrollableViewportSize() {
			return getPreferredSize();
        }
        public int getScrollableUnitIncrement
			(Rectangle visibleRect, int orientation, int direction) { return 105; }
        public int getScrollableBlockIncrement
			(Rectangle visibleRect, int orientation, int direction) { return 210; }
        public boolean getScrollableTracksViewportWidth() { return true; }
        public boolean getScrollableTracksViewportHeight() { return false; } 
	}
	
	private class ScrollFlowLayout extends FlowLayout {
		
		private static final long serialVersionUID = -8342595002999433301L;

		public Dimension preferredLayoutSize(Container target) {
			int hgap = getHgap(), vgap = getVgap();
			Dimension dim = new Dimension(0, 0);
			int nmembers = target.getComponentCount();
			boolean firstVisibleComponent = true, firstRow = true;
			Insets insets = target.getInsets();
			int maxwidth = target.getWidth() - (insets.left + insets.right + hgap*2);
			
			int curWidth = 0, curHeight = 0;
			for (int i = 0 ; i < nmembers ; i++) {
				Component m = target.getComponent(i);
				if (m.isVisible()) {
					Dimension d = m.getPreferredSize();
					if (firstVisibleComponent) {
						firstVisibleComponent = false;
					}
					else
						curWidth += hgap;
					curWidth += d.width;
					if (curWidth - hgap > maxwidth) {
						if (firstRow) 
							firstRow = false;
						else
							curHeight += vgap;
						dim.height += curHeight;
						curHeight = 0;
						curWidth -= d.width + hgap;
						if (curWidth > dim.width)
							dim.width = curWidth;
						curWidth = d.width;
					}
					if (d.height > curHeight)
						curHeight = d.height;
				}
			}
			if (!firstRow)
				curHeight += vgap;
			dim.height += curHeight;
			if (curWidth > dim.width)
				dim.width = curWidth;
			
			dim.width += insets.left + insets.right + hgap*2;
			dim.height += insets.top + insets.bottom + vgap*2;
			return dim;
		}
	}

}
