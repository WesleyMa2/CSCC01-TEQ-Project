//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

public class GUI extends JFrame {
	
	private JPanel mainPanel = new JPanel();
	private JLabel topHeading = new JLabel("Welcome to TEQ Data Aggregator - please select a fiscal year and file type, then upload a file to continue");
	
	// Allow 20 fiscal years max to be stored in application in order to not overload memory, this represents a 20 year period 
	// starting from 20 years prior to the current year. 
	private static String[] fiscalYears = new String[21];
	private JComboBox fiscalYearDropdown = new JComboBox(fiscalYears);
	private String[] fileTypes = {"Excel (.xlsx)"};
	private JComboBox fileTypeDropdown = new JComboBox(fileTypes);
	private JButton uploadButton = new JButton("Upload");

	public static void main(String[] args){
		//populate fiscalYears
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int counter = 0;
		for(int i = currentYear; i > currentYear - 21; i--){
			fiscalYears[counter] = (Integer.toString(i) + "-" + Integer.toString(i+1));
			counter++;
		}
		new GUI();
	}


	public GUI(){
		super("TEQ Data Aggregation Tool (TDAT)");
		setSize(700,300);
		setResizable(true);
		
		//use FlowLayout manager to organize panel components in a straight line (does not support window resizing without messing up order of components)
		//FlowLayout horizontal = new FlowLayout(FlowLayout.LEFT);
		
		// use GridLayout manager in order to ensure proper component placement during window resizing
		GridLayout gridLayout = new GridLayout(0,1);
		mainPanel.setLayout(gridLayout);
		mainPanel.add(topHeading);
		mainPanel.add(fiscalYearDropdown);
		mainPanel.add(fileTypeDropdown);
		mainPanel.add(uploadButton);
		this.add(mainPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	/*
	 * The method below was going to be used if we allowed the user to input individual fiscal years themselves 
	 * to be removed if current design is approved
	 */
	
	//private static void addNewFiscalYear(String fiscalYear){
		//List<Integer> fiscalYearsInt = new ArrayList<Integer>();
		//String[] newFiscalYears = new String[100];
		//int numItems = 0;
		//for(int x = 0; x < 100; x++){
			//String firstFourDigits = fiscalYears[x].substring(0,4);
			//fiscalYearsInt.add(Integer.valueOf(firstFourDigits));
			//numItems++;
		//}
		//if(numItems > 99){
			//System.out.println("Sorry! No space left to store new fiscal years.");
		//}
		
		//else{
			//fiscalYearsInt.add(Integer.valueOf(fiscalYear.substring(0,4)));
			//Collections.reverse(fiscalYearsInt);
			//for(int y = 0; y < numItems; y++){
				//int nextYear = fiscalYearsInt.get(y) + 1;
				//newFiscalYears[y] = Integer.toString(y) + "-" + Integer.toString(nextYear);
			//}
			//fiscalYears = newFiscalYears;
		//}
		

	//}
	
	
}
