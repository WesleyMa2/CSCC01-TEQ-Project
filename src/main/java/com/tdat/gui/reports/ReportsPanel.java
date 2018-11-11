package com.tdat.gui.reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tdat.gui.GenericPanel;

/*
 * A view for the reports panel in MainWindow.
 */
@SuppressWarnings("serial")
public class ReportsPanel extends GenericPanel {
	
	public ReportsPanel() {
		// Panel Title
		panelTitle = "Reports";
		
		// Layout such that all content is on the top left
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Panel where all the content is
		JPanel allContent = new JPanel(new GridBagLayout());
		allContent.setBackground(Color.white);
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		// Header section of the panel
		JLabel headerHTML = new JLabel("<html><h2 style='margin:0'>Reports</h2>"
				+ "<small>Generate graphical reports through this "
				+ "interface. Reports will be displayed in your default browser.</small></html>");
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		allContent.add(headerHTML, layoutConstraints);
		
		// Query section of the panel
		JLabel tdatqlHTML = new JLabel("<html><br/><h3 style='margin:0'>TDATQL</h3><small>"
				+ "Enter a TDATQL query below to perform specific actions.</small></html>");
		layoutConstraints.gridy = 1;
		layoutConstraints.ipady = 20;
		allContent.add(tdatqlHTML, layoutConstraints);
		JTextField tdatqlQuery = new JTextField();
		layoutConstraints.gridy = 2;
		layoutConstraints.ipady = 5;
		allContent.add(tdatqlQuery, layoutConstraints);
		JButton submitQuery = new JButton("Run Query");
		layoutConstraints.gridy = 3;
		allContent.add(submitQuery, layoutConstraints);
		
		// Reports to be generated list section of the panel
		JLabel currentReportsHTML = new JLabel("<html><br/><h3 style='margin:0'>Reports to be Generated</h3><small>"
				+ "Below, you can see a list of reports to be generated.</small></html>");
		layoutConstraints.gridy = 4;
		layoutConstraints.ipady = 20;
		allContent.add(currentReportsHTML, layoutConstraints);
		String tr[][] = {{"Row 1 Column 1","Row 1 Column 2","Row 1 Column 2"}, {"Row 2 Column 1","Row 2 Column 2","Row 2 Column 2"}};    
		String th[] = {"Report Title","Type of Report","Column Selected"}; 
		JTable currentReportsTable = new JTable();
		currentReportsTable.setModel(new DefaultTableModel(tr,th) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		JScrollPane scrollPane = new JScrollPane(currentReportsTable);
		scrollPane.setPreferredSize(new Dimension(600, 100));
		layoutConstraints.gridy = 5;
		layoutConstraints.ipadx = 0;
		layoutConstraints.ipady = 0;
		allContent.add(scrollPane, layoutConstraints);
		
		// Add button
		JButton addButton = new JButton("Add Report");
		layoutConstraints.gridy = 6;
		layoutConstraints.insets = new Insets(10,0,0,0);
		allContent.add(addButton, layoutConstraints);
		
		// Remove button
		JButton removeButton = new JButton("Remove Report");
		layoutConstraints.gridy = 7;
		layoutConstraints.insets = new Insets(0,0,0,0);
		allContent.add(removeButton, layoutConstraints);
		
		// Generate Button
		JButton generateButton = new JButton("Generate Report(s)");
		layoutConstraints.insets = new Insets(20,0,0,0);
		layoutConstraints.gridy = 8;
		allContent.add(generateButton, layoutConstraints);
		
		// Add all content to the panel
		this.add(allContent);
	}
	
}
