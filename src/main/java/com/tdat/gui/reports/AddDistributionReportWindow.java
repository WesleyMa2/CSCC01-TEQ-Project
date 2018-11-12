package com.tdat.gui.reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.gui.MainWindow;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;
import com.tdat.report.chart.DistributionChartScheme;

public class AddDistributionReportWindow {

	private final JFrame frame;
	
	public AddDistributionReportWindow() {
		frame = new JFrame(App.appTitle + ": Add Distribution Report");
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setResizable(true);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
		mainPanel.setBackground(Color.white);
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		// Add section title
		JLabel sectionTitleLabel = new JLabel("<html><h2 style='margin:0'>Add Distribution Report</h2>"
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
				
		// Drop down of all available columns
		JLabel columnToGraphLabel = new JLabel("Select Column to Graph");
		layoutConstraints.gridy = 7;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(columnToGraphLabel, layoutConstraints);
		String[] columnToGraph = MasterData.getAllColumns().toArray(new String[0]);
		JComboBox<String[]> columnToGraphDropdown = new JComboBox(columnToGraph);
		layoutConstraints.gridy = 8;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(columnToGraphDropdown, layoutConstraints);
					
		// Style of graph Label and Selection
		JLabel styleOfGraphLabel = new JLabel("Style of Graph");
		layoutConstraints.gridy = 9;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(styleOfGraphLabel, layoutConstraints);
		ChartType[] styleOfGraph = {ChartType.BAR, ChartType.LINE};
		JComboBox<ChartType[]> styleOfGraphsDropdown = new JComboBox(styleOfGraph);
		layoutConstraints.gridy = 10;
		layoutConstraints.insets = new Insets(5,0,0,0);
		mainPanel.add(styleOfGraphsDropdown, layoutConstraints);
					
		// Generate graph button
		JButton addDistributionButton = new JButton("Add Graphical Report");
		layoutConstraints.gridy = 11;
		layoutConstraints.insets = new Insets(10,0,0,0);
		mainPanel.add(addDistributionButton, layoutConstraints);
		addDistributionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addReport(
							reportTitleTextField.getText(),
							xTitleTextField.getText(),
							yTitleTextField.getText(),
							columnToGraphDropdown.getSelectedItem().toString(),
							(ChartType)styleOfGraphsDropdown.getSelectedItem());
					String[] row = {Integer.toString(App.reportsList.size()),
							App.reportsList.get(App.reportsList.size()-1).getMainTitle(),
							App.reportsList.get(App.reportsList.size()-1).getGraphType().getjsonCode()
							};
					ReportsPanel.tableModel.addRow(row);
					frame.setVisible(false);
				} catch(NullPointerException a) {
					JFrame errorFrame = new JFrame();
					JOptionPane.showMessageDialog(errorFrame, "Please fill in all fields.");
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
	}
	
	private void addReport(String reportTitle, String xAxis, String yAxis, String column, ChartType chartType) {
		ChartScheme chartScheme = new DistributionChartScheme(column, chartType);
		chartScheme.setMainTitle(reportTitle);
		chartScheme.setXTitle(xAxis);
		chartScheme.setYTitle(yAxis);
		App.reportsList.add(chartScheme);
	}
	
}
