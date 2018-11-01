package com.tdat.graph;

import java.util.Map;

public abstract class GraphScheme {
    private String mainTitle = "";
    private String xTitle = "";
    private String yTitle = "";
    private boolean graphOrNot;

    public GraphScheme( boolean graphOrNot){
        this.graphOrNot = graphOrNot;
    }

    public GraphScheme setMainTitle (String title){
        this.mainTitle = title;
        return this;
    }

    public GraphScheme setXTitle (String title){
        this.xTitle = title;
        return this;
    }

    public GraphScheme setYTitle (String title){
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


}
