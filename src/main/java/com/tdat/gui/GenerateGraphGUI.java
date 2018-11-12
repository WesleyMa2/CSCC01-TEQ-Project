package com.tdat.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.tdat.data.MasterData;
import com.tdat.report.ChartJS;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;
import com.tdat.report.chart.DistributionChartScheme;

@SuppressWarnings("serial")
public class GenerateGraphGUI extends JFrame  {

	// Application Title
	private static final String applicationTitle = "TEQ Data Aggregation Tool (TDAT)";
	// Container
	private JPanel mainPanel = new JPanel();
	// Label with name of the application
	private JLabel applicationTitleLabel = new JLabel(applicationTitle, SwingConstants.CENTER);
	
	// Distribution selected
	private boolean distributionSelected = true;
	
	// Font types
	Font h1 = new Font("Arial", Font.BOLD,16);
	Font h2 = new Font("Arial", Font.BOLD,14);
	Font bold = new Font("Arial", Font.BOLD,12);
	Font regular = new Font("Arial", Font.PLAIN,12);

	public GenerateGraphGUI() {
		// Set the basics of the window title, look and size
		super(applicationTitle);
		Dimension windowMinSize = new Dimension(400, 400);
		setMinimumSize(windowMinSize);
		setSize(windowMinSize);
		setResizable(true);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setBackground(Color.white);
		
		GridLayout gridLayout = new GridLayout(0, 1);
		mainPanel.setLayout(gridLayout);
		
		// Add main header title
		applicationTitleLabel.setFont(h1);
		mainPanel.add(applicationTitleLabel);
		
		// Add section title
		JLabel sectionTitleLabel = new JLabel("Generate Graphical Reports");
		sectionTitleLabel.setFont(h2);
		mainPanel.add(sectionTitleLabel);
		
		// Report Title Label and Field
		JLabel reportTitleLabel = new JLabel("Enter Report Title");
		reportTitleLabel.setFont(regular);
		mainPanel.add(reportTitleLabel);
		JTextField reportTitleTextField = new JTextField();
		reportTitleTextField.setFont(regular);
		mainPanel.add(reportTitleTextField);
		
		// Report X-Axis Label and Field
		JLabel xTitleLabel = new JLabel("Enter X-axis Title");
		xTitleLabel.setFont(regular);
		mainPanel.add(xTitleLabel);
		JTextField xTitleTextField = new JTextField();
		xTitleTextField.setFont(regular);
		mainPanel.add(xTitleTextField);
		
		// Report Y-Axis Label and Field
		JLabel yTitleLabel = new JLabel("Enter Y-axis Title");
		yTitleLabel.setFont(regular);
		mainPanel.add(yTitleLabel);
		JTextField yTitleTextField = new JTextField();
		yTitleTextField.setFont(regular);
		mainPanel.add(yTitleTextField);
		
		//JCheckBox graphCheckBox = new JCheckBox("Check to graph");
		//graphCheckBox.setFont(regular);
		//mainPanel.add(graphCheckBox);
		
		// Type of Graph Label and Selection
		JLabel typeOfGraphLabel = new JLabel("Type of Graph");
		typeOfGraphLabel.setFont(regular);
		mainPanel.add(typeOfGraphLabel);
		String[] typeOfGraphs = {"Distribution", "Trend"};
		JComboBox<String[]> typeOfGraphsDropdown = new JComboBox(typeOfGraphs);
		mainPanel.add(typeOfGraphsDropdown);
		
		
		// DISTRIBUTION PANEL
		
		// Drop down of all available columns
		JLabel columnToGraphLabel = new JLabel("Select Column to Graph");
		columnToGraphLabel.setFont(regular);
		mainPanel.add(columnToGraphLabel);
		String[] columnToGraph = MasterData.getAllColumns().toArray(new String[0]);
		JComboBox<String[]> columnToGraphDropdown = new JComboBox(columnToGraph);
		mainPanel.add(columnToGraphDropdown);
			
		// Style of graph Label and Selection
		JLabel styleOfGraphLabel = new JLabel("Style of Graph");
		styleOfGraphLabel.setFont(regular);
		mainPanel.add(styleOfGraphLabel);
		String[] styleOfGraph = {"Line Graph", "Bar Graph"};
		JComboBox<String[]> styleOfGraphsDropdown = new JComboBox(styleOfGraph);
		mainPanel.add(styleOfGraphsDropdown);
			
		// Generate graph button
		JButton generateDistributionButton = new JButton("Generate Graph");
		generateDistributionButton.setFont(regular);
		mainPanel.add(generateDistributionButton);
			
		// Listens for when the generate button is clicked
		generateDistributionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
				// Get the graph type (bar or line)
				ChartType graphType = ChartType.BAR;
				if(styleOfGraphsDropdown.getSelectedItem().toString().equals("Line Graph")) {
					graphType = ChartType.LINE;
				}
					
				// Create a chart scheme and add its properties
				ChartScheme chartScheme = new DistributionChartScheme(columnToGraphDropdown.getSelectedItem().toString(), graphType);
				chartScheme.setMainTitle(reportTitleTextField.getText());
				chartScheme.setXTitle(xTitleTextField.getText());
				chartScheme.setYTitle(yTitleTextField.getText());
					
				// Put the chart scheme into JSON format
				String json = chartScheme.toJson();
				String path = ChartJS.create(json);
			        
				// FOR TESTING PURPOSES
				System.out.println("JSON:\t\t" + json);
				System.out.println("PATH:\t\t" + path);
			}
		});

		this.add(mainPanel);
	}

}
