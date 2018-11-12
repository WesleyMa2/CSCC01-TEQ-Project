package com.tdat.gui;

import com.tdat.query.CommandHandler;
import com.tdat.report.chart.ChartType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import com.tdat.report.ChartJS;
import com.tdat.data.analysis.SingleTableReader;
import com.tdat.report.chart.DistributionChartScheme;
import com.tdat.report.chart.ChartScheme;


/*
 * When the "Generate Report" button is clicked, the code in this.actionPerformed() is executed
 */
public class GenerateReportButtonListener implements ActionListener {
    ArrayList<String> jsonObjectSerializedToString = new ArrayList<String>();
    private SingleTableReader tableReader;

    public GenerateReportButtonListener() {

    }

    public void actionPerformed(ActionEvent e) {
        /* add Bekzod's JSON file generator in actionPerformed
         * If a file is selected in the JList, generate a report for that single uploaded file as mapped in fileUploadDict in GUI.java
         * If no file is selected in the JList, generate a report based on all uploaded files.
         */

        //TEST CODE
        String userQuery = "distribution of children with children-Over-Time;num-children;value as bar";
        CommandHandler.setupHandlers();
        String json = CommandHandler.handle(userQuery);
        System.out.println("JSON:\t\t" + json);
        String path = ChartJS.create(json);
        System.out.println("PATH:\t\t" + path);
    }


}
