package com.tdat.report.chart;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.analysis.SingleTableReader;
import com.tdat.report.JsonConverter;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph to show the trend of all entries of a given column over the years
 */
public class TrendChartScheme extends ChartScheme {

    private String column;

    public TrendChartScheme(String column, ChartType graphType) {
        super(graphType);
        this.column = column;
    }

    /**
     * A helper method to check to see if a given entry has already been seen in the given list of
     * ChartDataSets
     */
    private int containsEntry(List<ChartDataSet> dataSet, String entry) {
        int i;
        for (i = 0; i < dataSet.size(); i++) {
            if (dataSet.get(i).getHeader() == entry) {
                return i;
            }
        }
        return -1;
    }

    public String toJson() {
        // List of all entries in MasterData for given column
        List<ChartDataSet> chartDataList = new ArrayList<>();

        // Get all years in order
        List<Year> allYears = new ArrayList<>();
        allYears.addAll(MasterData.getServiceProvidedData().keySet());
        Collections.sort(allYears);

        // Iterate through the data for each year in MasterData
        for (Year year : allYears) {

            // Get the map of entry to entry count for the current year
            TableData dataForCurrentYear = MasterData.getYearData(year);
            SingleTableReader tableReader = new SingleTableReader(dataForCurrentYear);
            Map<String, Integer> entryCountForCurrentYear;
            try {
                entryCountForCurrentYear = tableReader.columnEntriesCount(this.column);
            } catch (ColumnNotFoundException e1) {
                entryCountForCurrentYear = new HashMap<>();
            }

            // Iterate through all entries in the current year
            for (String newEntry : entryCountForCurrentYear.keySet()) {

                // Get the number of the current entry
                int valueForCurrYear;
                if (entryCountForCurrentYear.containsKey(newEntry)) {
                    valueForCurrYear = entryCountForCurrentYear.get(newEntry);
                } else {
                    valueForCurrYear = 0;
                }

                // Add this value to the list of ChartDataSets
                int indexOfExistingChartData = containsEntry(chartDataList, newEntry);
                if (indexOfExistingChartData == -1) {
                    // Create a new ChartDataSet if this entry hasn't been seen before
                    List<Integer> newListOfData = new ArrayList<>();
                    newListOfData.add(valueForCurrYear);
                    chartDataList.add(new ChartDataSet(newEntry, newListOfData));
                } else {
                    chartDataList.get(indexOfExistingChartData).addData(valueForCurrYear);
                }
            }
        }

        // Create a new list of Years but as Strings
        List<String> allYearsString = new ArrayList<>();
        for (Year curr : allYears) {
            allYearsString.add(curr.toString());
        }

        return JsonConverter
            .serializeObject(this.getGraphType().getJsonCode(), this.getMainTitle(),
                this.getXTitle(), allYearsString, this.getYTitle(), chartDataList);
    }
}

