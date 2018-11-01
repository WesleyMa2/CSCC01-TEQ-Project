package com.tdat.graph;

import java.time.Year;

/**
 * Graph to show the distribution of the entries of a single column
 */
public class DistributionGraphScheme extends GraphScheme {
    private String column;

    public DistributionGraphScheme(String column, boolean graphOrNot){
        super(graphOrNot);
        this.column = column;
    }

    public String getColumnToGraph() {
        return column;
    }
}
