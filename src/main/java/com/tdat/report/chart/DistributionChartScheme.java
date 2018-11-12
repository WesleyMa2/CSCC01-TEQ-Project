package com.tdat.report.chart;

import com.tdat.data.*;
import com.tdat.data.analysis.SingleTableReader;
import com.tdat.report.JsonConverter;

import java.time.Year;
import java.util.*;

/**
 * Graph to show the distribution of the entries of a single column, with years being datasets
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

    /**
     * A method to return a json rep of the graph to be generated
     * @return
     */
    public String toJson(){
        // List of all entries in MasterData for given column
        List<String> allEntries = new ArrayList<>();
        List<ChartDataset> chartDataList = new ArrayList<>();

        // Iterate through the data for each year in MasterData
        for (Year year : MasterData.getAllData().keySet()) {
            TableData dataForCurrentYear = MasterData.getYearData(year);

            // Read all data in the current year
            SingleTableReader tableReader = new SingleTableReader(dataForCurrentYear);

            // Attempt to get the distribution of entries for the target column in the current year
            Map<String, Integer> entryCountForCurrentYear;
            try {
                entryCountForCurrentYear = tableReader.columnEntriesCount(this.column);
            } catch (ColumnNotFoundException e1) {
                // If the column does not exist for that year, do nothing
                entryCountForCurrentYear = new HashMap<>();
                e1.printStackTrace();
            }
            // Add new entries to the list off all entries
            for (String newEntry : entryCountForCurrentYear.keySet()){
                if (!allEntries.contains(newEntry)){
                    allEntries.add(newEntry);
                }
            }

            // Sort the list of all entries alphabetically
            Collections.sort(allEntries);
            List<Integer> listOfCounts = new ArrayList<>();

            // Add the entry count for each entry to a list that will go into a ChartDataset
            for (String entry : allEntries) {
                if (!entryCountForCurrentYear.containsKey(entry)){
                    listOfCounts.add(0);
                }else {
                    listOfCounts.add(entryCountForCurrentYear.get(entry));
                }
            }
            chartDataList.add(new ChartDataset(year.toString(),listOfCounts));
        }
        return JsonConverter.serializeObject(this.getGraphType().getjsonCode(), this.getMainTitle(), 
        		this.getxTitle(), allEntries, this.getyTitle(), chartDataList);

    }
}
