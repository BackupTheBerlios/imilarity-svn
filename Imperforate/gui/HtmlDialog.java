package gui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class HtmlDialog extends JDialog {

	private static final long serialVersionUID = -7130968580432750113L;

	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	
	public HtmlDialog(Frame owner, String title) {
		super(owner, title);
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		scrollPane = new JScrollPane(editorPane);
		getContentPane().add(scrollPane);
		setSize(new Dimension(800,600));
	}
	
	public HtmlDialog(Frame owner, String title, String html) {
		this(owner, title);
		setHtml(html);
	}
	
	public void setHtml(String html) {
		editorPane.setText(html);
		editorPane.setCaretPosition(0);
	}
}
