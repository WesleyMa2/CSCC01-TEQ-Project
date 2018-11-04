package com.tdat.gui;

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

        ChartScheme testGraph = new DistributionChartScheme("children", true, ChartScheme.BAR);
        testGraph.setMainTitle("Test Graph").setXTitle("Num children").setYTitle("Count");


//		ChartDataset data1 = new ChartDataset("2016", Arrays.asList(1,2,3,4,5));
//		ChartDataset data2 = new ChartDataset("2017", Arrays.asList(6,7,8,9,10));
//		ChartDataset data3 = new ChartDataset("2018", Arrays.asList(1,3,5,7,9));
//      String json = JsonConverter.serializeObject(testGraph.getGraphType(), testGraph.getxTitle(), columnEntriesCount.keySet(), testGraph.getyTitle(), Arrays.asList(data1, data2, data3));

        String json = ((DistributionChartScheme) testGraph).toJson();
        System.out.println("JSON:\t\t" + json);
        jsonObjectSerializedToString.add(json);

        String path = ChartJS.create(json);
        System.out.println("PATH:\t\t" + path);
    }


}
