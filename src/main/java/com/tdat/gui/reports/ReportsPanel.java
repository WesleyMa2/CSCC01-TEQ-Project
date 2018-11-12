package com.tdat.gui.reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tdat.app.App;
import com.tdat.gui.GenericPanel;

/*
 * A view for the reports panel in MainWindow.
 */
@SuppressWarnings("serial")
public class ReportsPanel extends GenericPanel {
	
	protected static DefaultTableModel tableModel = new DefaultTableModel() {
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public ReportsPanel() {
		// Panel Title
		panelTitle = "Reports";
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		// Header section of the panel
		JLabel headerHTML = new JLabel("<html><h2 style='margin:0'>Reports</h2>"
				+ "<small>Generate graphical reports through this "
				+ "interface. Reports will be displayed in your default browser.</small></html>");
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		layoutConstraints.weightx = 1;
		layoutConstraints.weighty = 1;
		layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
		this.add(headerHTML, layoutConstraints);
		
		// Query section of the panel
		JLabel tdatqlHTML = new JLabel("<html><br/><h3 style='margin:0'>TDATQL</h3><small>"
				+ "Enter a TDATQL query below to perform specific actions.</small></html>");
		layoutConstraints.gridy = 1;
		layoutConstraints.ipady = 20;
		this.add(tdatqlHTML, layoutConstraints);
		JTextField tdatqlQuery = new JTextField();
		layoutConstraints.gridy = 2;
		layoutConstraints.ipady = 5;
		this.add(tdatqlQuery, layoutConstraints);
		JButton submitQuery = new JButton("Run Query");
		layoutConstraints.gridy = 3;
		this.add(submitQuery, layoutConstraints);
		submitQuery.addActionListener(new TDATQLListener(tdatqlQuery.getText()));
		
		// Reports to be generated list section of the panel
		JLabel currentReportsHTML = new JLabel("<html><br/><h3 style='margin:0'>Reports to be Generated</h3><small>"
				+ "Below, you can see a list of reports to be generated.</small></html>");
		layoutConstraints.gridy = 4;
		layoutConstraints.ipady = 20;
		this.add(currentReportsHTML, layoutConstraints);
		String tr[][] = new String[App.reportsList.size()][3];
		for(int index = 0; index < App.reportsList.size(); index++) {
			tr[index][0] = (Integer.toString(index+1));
			tr[index][1] = App.reportsList.get(index).getMainTitle();
			tr[index][2] = App.reportsList.get(index).getGraphType().getjsonCode();
		} 
		String th[] = {"Report Menu", "Report Title","Type of Report"}; 
		JTable currentReportsTable = new JTable();
		tableModel.setDataVector(tr, th);
		currentReportsTable.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane(currentReportsTable);
		scrollPane.setPreferredSize(new Dimension(600, 100));
		layoutConstraints.gridy = 5;
		layoutConstraints.ipadx = 0;
		layoutConstraints.ipady = 0;
		this.add(scrollPane, layoutConstraints);
		
		// Add button
		JButton addTrendsButton = new JButton("Add Trends Chart Report");
		layoutConstraints.gridy = 6;
		layoutConstraints.insets = new Insets(10,0,0,0);
		this.add(addTrendsButton, layoutConstraints);
		addTrendsButton.addActionListener(new AddTrendsReportListener());
		
		// Add button
		JButton addDistributionButton = new JButton("Add Distribution Chart Report");
		layoutConstraints.gridy = 7;
		layoutConstraints.insets = new Insets(0,0,0,0);
		this.add(addDistributionButton, layoutConstraints);
		addDistributionButton.addActionListener(new AddDistributionReportListener());
		
		// Remove button
		JButton removeButton = new JButton("Remove Report");
		layoutConstraints.gridy = 8;
		this.add(removeButton, layoutConstraints);
		removeButton.addActionListener(new RemoveReportListener());
		
		// Generate Button
		JButton generateButton = new JButton("Generate Report(s)");
		layoutConstraints.insets = new Insets(20,0,0,0);
		layoutConstraints.gridy = 9;
		this.add(generateButton, layoutConstraints);
		generateButton.addActionListener(new GenerateReportListener());
		
	}
	
}
