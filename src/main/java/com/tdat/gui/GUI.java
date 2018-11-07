package com.tdat.gui;

import com.tdat.app.App;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.io.File;
import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String applicationTitle = "TEQ Data Aggregation Tool (TDAT)";
	private static final String Dimension = null;
	
	// Container
	private JPanel mainPanel = new JPanel();
	// Label with name of the application
	private JLabel applicationTitleLabel = new JLabel(applicationTitle);
	// Label with basic instructions
	private JLabel topHeading = new JLabel("Please select a fiscal year and file type, then upload a file to continue.");

	// Allow 20 fiscal years max to be stored in application in order to not
	// overload memory, this represents a 20 year period
	// starting from 20 years prior to the current year.
	private static String[] fiscalYears = new String[21];
	private JComboBox fiscalYearDropdown = new JComboBox(fiscalYears);
	private String[] fileTypes = { "Excel (.xlsx)" };
	private JComboBox fileTypeDropdown = new JComboBox(fileTypes);
	private JButton uploadButton = new JButton("Upload");
	private JButton graphGenerateButton = new JButton("Generate Graph");
	private JButton reportGenerateButton = new JButton("Generate Report(s)");

	static DefaultListModel DLM = new DefaultListModel();
	JList historyList = new JList(DLM);

	// A dictionary to map upload time to the actual file object uploaded. This is
	// to be used in conjunction with the "Generate Report" button
	// so that we can generate reports for specific files uploaded
	static HashMap<String, File> fileUploadDict = new HashMap<String, File>();

//	// A dictionary to map the number of times a value occurs in a column chosen as
//	// part of this GUI
//	static Map<String, Integer> columnValueInstanceCounterMap = new HashMap<String, Integer>();

	public static void main(String[] args) {
		// populate fiscalYears
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int counter = 0;
		for (int i = currentYear; i > currentYear - 21; i--) {
			fiscalYears[counter] = (Integer.toString(i) + "-" + Integer.toString(i + 1));
			counter++;
		}

		new GUI();
	}

	public GUI() {
		super(applicationTitle);
		Dimension windowMinSize = new Dimension(400, 300);
		setMinimumSize(windowMinSize);
		setSize(windowMinSize);
		setResizable(true);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setBackground(Color.white);

		// use FlowLayout manager to organize panel components in a straight line (does
		// not support window resizing without messing up order of components)
		// FlowLayout horizontal = new FlowLayout(FlowLayout.LEFT);

		// use GridLayout manager in order to ensure proper component placement during
		// window resizing
		GridLayout gridLayout = new GridLayout(0, 1);
		mainPanel.setLayout(gridLayout);
		mainPanel.add(applicationTitleLabel);
		mainPanel.add(topHeading);
		mainPanel.add(fiscalYearDropdown);
		mainPanel.add(fileTypeDropdown);
		mainPanel.add(historyList);
		mainPanel.add(uploadButton);
		mainPanel.add(graphGenerateButton);
		mainPanel.add(reportGenerateButton);

		// BUTTON ACTIONS
		fiscalYearDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.selectedYear = (Year.of(Integer.parseInt((fiscalYearDropdown.getSelectedItem().toString()).substring(0,4))));
			}
		});

		fileTypeDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.selectedFileType = fileTypeDropdown.getSelectedItem().toString();
			}
		});

		uploadButton.addActionListener(new UploadButtonListener());
		
		graphGenerateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GenerateGraphGUI().setVisible(true);
			}
		});
		
		reportGenerateButton.addActionListener(new GenerateReportButtonListener());
		this.add(mainPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/*
	 * The method below was going to be used if we allowed the user to input
	 * individual fiscal years themselves to be removed if current design is
	 * approved
	 */

	// private static void addNewFiscalYear(String fiscalYear){
	// List<Integer> fiscalYearsInt = new ArrayList<Integer>();
	// String[] newFiscalYears = new String[100];
	// int numItems = 0;
	// for(int x = 0; x < 100; x++){
	// String firstFourDigits = fiscalYears[x].substring(0,4);
	// fiscalYearsInt.add(Integer.valueOf(firstFourDigits));
	// numItems++;
	// }
	// if(numItems > 99){
	// System.out.println("Sorry! No space left to store new fiscal years.");
	// }

	// else{
	// fiscalYearsInt.add(Integer.valueOf(fiscalYear.substring(0,4)));
	// Collections.reverse(fiscalYearsInt);
	// for(int y = 0; y < numItems; y++){
	// int nextYear = fiscalYearsInt.get(y) + 1;
	// newFiscalYears[y] = Integer.toString(y) + "-" + Integer.toString(nextYear);
	// }
	// fiscalYears = newFiscalYears;
	// }

	// }

}
