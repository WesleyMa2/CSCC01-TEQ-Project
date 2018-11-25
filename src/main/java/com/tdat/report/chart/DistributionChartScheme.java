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

    public DistributionChartScheme(String column, ChartType graphType){
        super(graphType);
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public String toJson(){
        // List of all entries in MasterData for given column
        List<String> allEntries = new ArrayList<>();
        List<ChartDataSet> chartDataList = new ArrayList<>();

        // Iterate through the data for each year in MasterData
        for (Year year : MasterData.getServiceProvidedData().keySet()) {
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
                if (!allEntries.contains(newEntry)){
                    allEntries.add(newEntry);
                }
            }
            Collections.sort(allEntries);
            List<Integer> listOfCounts = new ArrayList<>();
            for (String entry : allEntries) {
                if (!entryCountForCurrentYear.containsKey(entry)){
                    listOfCounts.add(0);
                }else {
                    listOfCounts.add(entryCountForCurrentYear.get(entry));
                }
            }
            chartDataList.add(new ChartDataSet(year.toString(),listOfCounts));
        }
        return JsonConverter.serializeObject(this.getGraphType().getJsonCode(), this.getMainTitle(), 
        		this.getXTitle(), allEntries, this.getYTitle(), chartDataList);

    }
}
