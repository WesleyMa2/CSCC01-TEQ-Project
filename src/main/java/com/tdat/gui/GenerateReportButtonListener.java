package com.tdat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.tdat.app.App;
import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.JsonConverter;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.analysis.SingleTableReader;


/*
 * When the "Generate Report" button is clicked, the code in this.actionPerformed() is executed 
 */
public class GenerateReportButtonListener implements ActionListener{
	ArrayList<String> jsonObjectSerializedToString = new ArrayList<String>();
    private SingleTableReader tableReader;
	
    public GenerateReportButtonListener() {

    }
    
    public void actionPerformed(ActionEvent e) {
    	/* add Bekzod's JSON file generator in actionPerformed
    	 * If a file is selected in the JList, generate a report for that single uploaded file as mapped in fileUploadDict in GUI.java
    	 * If no file is selected in the JList, generate a report based on all uploaded files. 
    	 */
    	
    	TableData data = MasterData.getYearData(App.selectedYear);
    	System.out.println(App.selectedYear);
    	tableReader = new SingleTableReader(data);
    	
    	String json;
		try {
			String column = "Date of Birth (YYYY-MM-DD)";
			json = JsonConverter.serializeObject(column, tableReader.columnEntriesCount(column));
	    	
	    	System.out.println("JSON:\t\t" + json);
	    	jsonObjectSerializedToString.add(json);
		} catch (ColumnNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    

}
