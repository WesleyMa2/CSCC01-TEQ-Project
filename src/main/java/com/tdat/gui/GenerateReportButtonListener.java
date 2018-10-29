package com.tdat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * When the "Generate Report" button is clicked, the code in this.actionPerformed() is executed 
 */
public class GenerateReportButtonListener implements ActionListener{
	
	public GenerateReportButtonListener(){
	}
	
    public void actionPerformed(ActionEvent e) {
    	//add Bekzod's JSON file generator in actionPerformed
    	//If a file is selected in the JList, generate a report for that single uploaded file as mapped in fileUploadDict in GUI.java
    	//If no file is selected in the JList, generate a report based on all uploaded files.
    }
    

}
