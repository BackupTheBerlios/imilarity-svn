package gui;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class HtmlFrame extends JFrame {

	private static final long serialVersionUID = -7130968580432750113L;

	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	
	public HtmlFrame(String title) {
		super(title);
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		scrollPane = new JScrollPane(editorPane);
		getContentPane().add(scrollPane);
		pack();
		setSize(new Dimension(800,600));
		setVisible(true);
	}
	
	public HtmlFrame(String title, String html) {
		this(title);
		setHtml(html);
	}
	
	public void setHtml(String html) {
		editorPane.setText(html);
		editorPane.setCaretPosition(0);
		repaint();
		//pack();
		//setSize(new Dimension(800,600));
	}
}
