package com.tdat.report.chart;

import com.tdat.data.*;
import com.tdat.data.analysis.SingleTableReader;
import com.tdat.report.JsonConverter;

import java.time.Year;
import java.util.*;

/**
 * Graph to show the distribution of the entries of a single column
 */
public class DistributionChartScheme extends ChartScheme {
    private String column;

    public DistributionChartScheme(String column, boolean graphOrNot, String graphType){
        super(graphOrNot, graphType);
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public String toJson(){
        List<String> entries = new ArrayList<>();
        List<ChartDataset> chartDataList = new ArrayList<>();
        for (Year year : MasterData.getAllData().keySet()) {
            TableData dataForCurrentYear = MasterData.getYearData(year);
            SingleTableReader tableReader = new SingleTableReader(dataForCurrentYear);

            Map<String, Integer> entryCountForCurrentYear;
            try {
                entryCountForCurrentYear = tableReader.columnEntriesCount(this.column);
            } catch (ColumnNotFoundException e1) {
                entryCountForCurrentYear = new HashMap<>();
                e1.printStackTrace();
            }
            for (String newEntry : entryCountForCurrentYear.keySet()){
                if (!entries.contains(newEntry)){
                    entries.add(newEntry);
                }
            }
            Collections.sort(entries);
            List<Integer> listOfCounts = new ArrayList<>();
            for (String entry : entries) {
                if (!entryCountForCurrentYear.containsKey(entry)){
                    listOfCounts.add(0);
                }else {
                    listOfCounts.add(entryCountForCurrentYear.get(entry));
                }
            }
            chartDataList.add(new ChartDataset(year.toString(),listOfCounts));
        }


//		ChartDataset data1 = new ChartDataset("2016", Arrays.asList(1,2,3,4,5));
//		ChartDataset data2 = new ChartDataset("2017", Arrays.asList(6,7,8,9,10));
//		ChartDataset data3 = new ChartDataset("2018", Arrays.asList(1,3,5,7,9));
        return JsonConverter.serializeObject(this.getGraphType(), getxTitle(), entries, getyTitle(), chartDataList);

    }
}
