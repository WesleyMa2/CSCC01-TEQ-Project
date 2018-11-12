package com.tdat.report.chart;

/**
 * A class to store information about a graph to be generated
 */
public abstract class ChartScheme {
    private String mainTitle = "";
    private String xTitle = "";
    private String yTitle = "";
    private ChartType graphType;

    public ChartScheme(ChartType graphType){
        this.graphType = graphType;
    }

    public ChartScheme setMainTitle (String title){
        this.mainTitle = title;
        return this;
    }

    public ChartScheme setXTitle (String title){
        this.xTitle = title;
        return this;
    }

    public ChartScheme setYTitle (String title){
        this.yTitle = title;
        return this;
    }

    public abstract String toJson();

    public String getMainTitle() {
        return mainTitle;
    }

    public String getxTitle() {
        return xTitle;
    }

    public String getyTitle() {
        return yTitle;
    }

    public ChartType getGraphType() {
        return graphType;
    }



}
