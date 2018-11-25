package com.tdat.gui.reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

  private static JTextField tdatqlQuery = new JTextField();

  protected static DefaultTableModel tableModel = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };

  public static JTextField getTdatqlQuery() {
    return tdatqlQuery;
  }

  public ReportsPanel() {
    // Panel Title
    panelTitle = "Reports";

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.white);
    GridBagConstraints layoutConstraints = new GridBagConstraints();

    // Header section of the panel
    JLabel headerHTML = new JLabel(
        "<html><h2 style='margin:0'>Reports</h2>" + "<small>Generate graphical reports through this "
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
        + "Enter a TDATQL query below to perform specific actions.<br/> Try 'distribution of children with children-Over-Time;num-children;value as bar'</small></html>");
    layoutConstraints.gridy = 1;
    layoutConstraints.ipady = 20;
    this.add(tdatqlHTML, layoutConstraints);

    layoutConstraints.gridy = 2;
    layoutConstraints.ipady = 5;
    this.add(tdatqlQuery, layoutConstraints);
    JButton submitQuery = new JButton("Run Query");
    layoutConstraints.gridy = 3;
    this.add(submitQuery, layoutConstraints);
    submitQuery.addActionListener(new TDATQLListener());

    // Reports to be generated list section of the panel
    JLabel currentReportsHTML = new JLabel("<html><br/><h3 style='margin:0'>Charts to be added</h3><small>"
        + "Below, you can see a list of charts to be added in the report.</small></html>");
    layoutConstraints.gridy = 4;
    layoutConstraints.ipady = 20;
    this.add(currentReportsHTML, layoutConstraints);

    String th[] = { "Id", "Title", "Type" };
    JTable currentReportsTable = new JTable();
    tableModel.setDataVector(null, th);
    currentReportsTable.setModel(tableModel);
    JScrollPane scrollPane = new JScrollPane(currentReportsTable);
    scrollPane.setPreferredSize(new Dimension(600, 100));
    layoutConstraints.gridy = 5;
    layoutConstraints.ipadx = 0;
    layoutConstraints.ipady = 0;
    this.add(scrollPane, layoutConstraints);

    // Add button
    JButton addTrendsButton = new JButton("Add Trends Chart");
    layoutConstraints.gridy = 6;
    layoutConstraints.insets = new Insets(10, 0, 0, 0);
    this.add(addTrendsButton, layoutConstraints);
    addTrendsButton.addActionListener(new AddTrendsReportListener());

    // Add button
    JButton addDistributionButton = new JButton("Add Distribution Chart");
    layoutConstraints.gridy = 7;
    layoutConstraints.insets = new Insets(0, 0, 0, 0);
    this.add(addDistributionButton, layoutConstraints);
    addDistributionButton.addActionListener(new AddDistributionReportListener());

    // Remove button
    JButton removeButton = new JButton("Remove Chart");
    layoutConstraints.gridy = 8;
    this.add(removeButton, layoutConstraints);
    removeButton.addActionListener(new RemoveReportListener(currentReportsTable));

    // Generate Button
    JButton generateButton = new JButton("Generate Report");
    layoutConstraints.insets = new Insets(20, 0, 0, 0);
    layoutConstraints.gridy = 9;
    this.add(generateButton, layoutConstraints);
    generateButton.addActionListener(new GenerateReportListener());

  }

}
