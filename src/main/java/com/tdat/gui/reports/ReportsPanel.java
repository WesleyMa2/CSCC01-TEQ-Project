package com.tdat.gui;

import javax.swing.JLabel;

/*
 * A view for the reports panel in MainWindow.
 */
@SuppressWarnings("serial")
public class ReportsPanel extends GenericPanel {

	public ReportsPanel() {
		// Panel Title
		panelTitle = "Reports";
		
		JLabel instructionsHTML = new JLabel("To be added.");
		this.add(instructionsHTML);
	}
	
}
