package com.tdat.report.chart.templates;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.data.analysis.ServiceReceivedVerifier;
import com.tdat.gui.reports.ReportsPanel;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;
import com.tdat.report.chart.ServiceReceivedChartScheme;

public class ServiceReceivedTemplate implements Template {

	private static final String title = "Services Received Template";
	private static final String description = "Add a chart to display the amount of people who receive the services " + 
	"they were referred to.";
	private static final String mainTitle = "Services Referred vs. Services Received";
	private static final String yAxis = "Number of Referred/Received";
	private static final String xAxis = "Service";
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}

	public void addWindow() {
		JFrame frame = new JFrame(App.appTitle + ": Use " + title);;
		frame.setMinimumSize(new Dimension(400, 450));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
        mainPanel.setBackground(Color.white);
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        
        // Section title
        JLabel sectionTitleLabel = new JLabel(
            "<html><h2 style='margin:0'>" + title + "</h2></html>");
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.weightx = 1;
        layoutConstraints.weighty = 1;
        layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(sectionTitleLabel, layoutConstraints);

        // Style of graph Label and Selection
        JLabel styleOfGraphLabel = new JLabel("Style of Graph");
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(styleOfGraphLabel, layoutConstraints);
        ChartType[] styleOfGraph = {ChartType.BAR, ChartType.LINE};
        JComboBox<ChartType[]> styleOfGraphsDropdown = new JComboBox(styleOfGraph);
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = new Insets(5, 0, 0, 0);
        mainPanel.add(styleOfGraphsDropdown, layoutConstraints);
        
        JButton addButton = new JButton("Add Graphs by Template");
        layoutConstraints.gridy = 4;
        layoutConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(addButton, layoutConstraints);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ChartType type = ChartType.getChartTypeFromString(
            			styleOfGraphsDropdown.getSelectedItem().toString());
            	ChartScheme chart = addReports(type);

            	String[] row = {
            			Integer.toString(MasterData.reportId.incrementAndGet()),
            			chart.getMainTitle(),
            			chart.getGraphType().getJsonCode()
            	};

                ReportsPanel.tableModel.addRow(row);
            	frame.setVisible(false);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
	}
	
	private ChartScheme addReports(ChartType graphType) {
		ChartScheme scheme = new ServiceReceivedChartScheme(graphType);
		scheme.setMainTitle(mainTitle);
		scheme.setXTitle(xAxis);
		scheme.setYTitle(yAxis);
		App.reportsList.add(scheme);
		return scheme;
	}

	public boolean usable() {
		Boolean usable = true;
		
		if(ServiceReceivedVerifier.getReferralsCount().size() == 0) {
			usable = false;
		}
		
		return usable;
	}
}
