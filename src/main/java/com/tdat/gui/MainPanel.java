package com.tdat.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import com.tdat.app.App;

@SuppressWarnings("serial")
public class MainPanel extends GenericPanel {
	
	public MainPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		panelTitle = "Main";
		
		String mainHTML = "<html>"
				+ "<h1>" + App.appTitle + "</h1>"
				+ "<hr/>"
				+ "<br/>"
				+ "<p><b>Greetings!</b> Navigate through the upload and reports section using the tabs on the left.</p>\r\n" + 
				"<br/>\r\n" + 
				"<small>\r\n" + 
				"<b>Acknowledgements:</b><br/>\r\n" + 
				"Thierry Sans - Professor<br/>\r\n" + 
				"Bekzod Tursunov - Developer<br/>\r\n" + 
				"Radu Laudat - Developer<br/>\r\n" + 
				"Alvin Tang - Developer<br/>\r\n" + 
				"Wesley Ma - Developer\r\n" + 
				"</ul>\r\n" + 
				"</b>\r\n";
		
		JLabel mainLabel = new JLabel(mainHTML);
		this.add(mainLabel);
	}
	
}
