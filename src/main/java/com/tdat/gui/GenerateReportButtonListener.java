package com.tdat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.tdat.data.JsonConverter;


/*
 * When the "Generate Report" button is clicked, the code in this.actionPerformed() is executed 
 */
public class GenerateReportButtonListener implements ActionListener{
	JsonConverter jsonConverterInstance;
	ArrayList<String> jsonObjectSerializedToString = new ArrayList<String>();
	
	public GenerateReportButtonListener(){
		jsonConverterInstance = new JsonConverter();
	}
	
    public void actionPerformed(ActionEvent e) {
    	/* add Bekzod's JSON file generator in actionPerformed
    	 * If a file is selected in the JList, generate a report for that single uploaded file as mapped in fileUploadDict in GUI.java
    	 * If no file is selected in the JList, generate a report based on all uploaded files. 
    	 */
    	jsonObjectSerializedToString.add(jsonConverterInstance.SerializeObject(GUI.columnValueInstanceCounterMap));
    	
    }
    

}
