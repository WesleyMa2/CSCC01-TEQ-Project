package com.tdat.report.chart;

public abstract class ChartScheme {
    public static String LINE = "line";
    public static String BAR = "bar";
    private String mainTitle = "";
    private String xTitle = "";
    private String yTitle = "";
    private boolean graphOrNot;
    private String graphType;

    public ChartScheme(boolean graphOrNot, String graphType){
        this.graphOrNot = graphOrNot;
        this.graphType = graphType;
    }

    public ChartScheme setMainTitle (String title){
        this.mainTitle = title;
        this.graphType = graphType;
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

    public boolean graphOrNot(){return graphOrNot;}

    public String getMainTitle() {
        return mainTitle;
    }

    public String getxTitle() {
        return xTitle;
    }

    public String getyTitle() {
        return yTitle;
    }

    public String getGraphType() {
        return graphType;
    }



}
