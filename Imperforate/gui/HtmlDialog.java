package gui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class HtmlDialog extends JFrame {

	private static final long serialVersionUID = -7130968580432750113L;

	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	
	public HtmlDialog(Frame owner, String title) {
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
	
	public HtmlDialog(Frame owner, String title, String html) {
		this(owner, title);
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
