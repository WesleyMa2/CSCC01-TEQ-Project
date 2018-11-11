package com.tdat.gui.reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.TrendChartScheme;

public class AddTrendReportWindow {

	private final JFrame frame;
	
	public AddTrendReportWindow() {
		frame = new JFrame(App.appTitle + ": Add Trend Report");
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setResizable(true);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
		mainPanel.setBackground(Color.white);
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		// Add section title
		JLabel sectionTitleLabel = new JLabel("<html><h2 style='margin:0'>Add Trend Report</h2>"
				+ "<small>Enter the details of the new report below.</small></html>");
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		layoutConstraints.weightx = 1;
		layoutConstraints.weighty = 1;
		layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
		mainPanel.add(sectionTitleLabel, layoutConstraints);
		
		// Report Title Label and Field
		JLabel reportTitleLabel = new JLabel("Report Title");
		layoutConstraints.gridy = 1;
		layoutConstraints.insets = new Insets(15,0,0,0);
		mainPanel.add(reportTitleLabel, layoutConstraints);
		JTextField reportTitleTextField = new JTextField();
		layoutConstraints.gridy = 2;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(reportTitleTextField, layoutConstraints);
				
		// Report X-Axis Label and Field
		JLabel xTitleLabel = new JLabel("Enter X-axis Title");
		layoutConstraints.gridy = 3;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(xTitleLabel, layoutConstraints);
		JTextField xTitleTextField = new JTextField();
		layoutConstraints.gridy = 4;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(xTitleTextField, layoutConstraints);
				
		// Report Y-Axis Label and Field
		JLabel yTitleLabel = new JLabel("Enter Y-axis Title");
		layoutConstraints.gridy = 5;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(yTitleLabel, layoutConstraints);
		JTextField yTitleTextField = new JTextField();
		layoutConstraints.gridy = 6;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(yTitleTextField, layoutConstraints);
				
		// Type of Graph Label and Selection
		JLabel typeOfGraphLabel = new JLabel("Type of Graph");
		layoutConstraints.gridy = 7;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(typeOfGraphLabel, layoutConstraints);
		String[] typeOfGraphs = {"Distribution", "Trend"};
		JComboBox<String[]> typeOfGraphsDropdown = new JComboBox(typeOfGraphs);
		layoutConstraints.gridy = 8;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(typeOfGraphsDropdown, layoutConstraints);
				
		// Drop down of all available columns
		JLabel columnToGraphLabel = new JLabel("Select Column to Graph");
		layoutConstraints.gridy = 9;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(columnToGraphLabel, layoutConstraints);
		String[] columnToGraph = MasterData.getAllColumns().toArray(new String[0]);
		JComboBox<String[]> columnToGraphDropdown = new JComboBox(columnToGraph);
		layoutConstraints.gridy = 10;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(columnToGraphDropdown, layoutConstraints);
					
		// Style of graph Label and Selection
		JLabel styleOfGraphLabel = new JLabel("Style of Graph");
		layoutConstraints.gridy = 11;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(styleOfGraphLabel, layoutConstraints);
		String[] styleOfGraph = {"Line Graph", "Bar Graph"};
		JComboBox<String[]> styleOfGraphsDropdown = new JComboBox(styleOfGraph);
		layoutConstraints.gridy = 12;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(styleOfGraphsDropdown, layoutConstraints);
					
		// Generate graph button
		JButton addTrendButton = new JButton("Add Graphical Report");
		layoutConstraints.gridy = 13;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(addTrendButton, layoutConstraints);
		
		
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
	}
	
}
