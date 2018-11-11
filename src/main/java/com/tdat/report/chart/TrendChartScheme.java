package com.tdat.report.chart;

import java.util.Map;

/**
 * Graph to show the trend of various column entries over the years
 */
public class TrendChartScheme extends ChartScheme {
    private Map<String, String> dataSet;

    public TrendChartScheme(Map<String, String> dataSet, String graphType) {
        super(graphType);
        this.dataSet = dataSet;
    }

    public String toJson(){return null;}

    public Map<String, String> getDataSetToGraph() {
        return dataSet;
    }
}

