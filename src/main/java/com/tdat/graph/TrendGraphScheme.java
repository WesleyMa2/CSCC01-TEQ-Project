package com.tdat.graph;

import java.util.Map;

/**
 * Graph to show the trend of various column entries over the years
 */
public class TrendGraphScheme extends GraphScheme{
    private Map<String, String> dataSet;

    public TrendGraphScheme(Map<String, String> dataSet, boolean graphOrNot) {
        super(graphOrNot);
        this.dataSet = dataSet;
    }

    public Map<String, String> getDataSetToGraph() {
        return dataSet;
    }
}

