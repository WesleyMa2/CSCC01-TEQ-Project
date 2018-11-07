package com.tdat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import com.tdat.app.App;
import com.tdat.data.ChartData;
import com.tdat.data.ChartJS;
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
    	
		ChartData data1 = new ChartData("2016", Arrays.asList(1,2,3,4,5));
		ChartData data2 = new ChartData("2017", Arrays.asList(6,7,8,9,10));
		ChartData data3 = new ChartData("2018", Arrays.asList(1,3,5,7,9));
		String json = JsonConverter.serializeObject("line", "# Of Children", Arrays.asList("1","2", "3", "4", "5+"), "Value", Arrays.asList(data1, data2, data3));

		System.out.println("JSON:\t\t" + json);
		jsonObjectSerializedToString.add(json);

		String path = ChartJS.create(json);
		System.out.println("PATH:\t\t" + path);
    }
    

}
