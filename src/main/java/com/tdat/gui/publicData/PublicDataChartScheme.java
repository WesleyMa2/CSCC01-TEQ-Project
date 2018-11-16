package com.tdat.gui.publicData;

import java.util.ArrayList;
import java.util.List;

import com.tdat.report.JsonConverter;
import com.tdat.report.chart.ChartDataSet;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Graph to show the distribution of the entries of a single column, with years being datasets
 */
public class PublicDataChartScheme extends ChartScheme {

    private List<String> xAxisLabels;
    private List<ChartDataSet> dataSet;

    public PublicDataChartScheme(JSONObject json) {
        super(ChartType.getChartTypeFromString(json.getString("type")));

        this.setMainTitle(json.getString("mainTitle"));
        this.setXTitle(json.getString("xAxisTitle"));
        this.setYTitle(json.getString("yAxisTitle"));

        xAxisLabels = new ArrayList<String>();
        JSONArray arr = json.getJSONArray("xAxisLabels");
        for (int i = 0; i < arr.length(); i++) {
            xAxisLabels.add(arr.getString(i));
        }

        dataSet = new ArrayList<ChartDataSet>();
        arr = json.getJSONArray("dataSet");
        for (int i = 0; i < arr.length(); i++) {
            JSONArray jsonData = arr.getJSONObject(i).getJSONArray("data");
            List<Integer> data = new ArrayList<Integer>();

            for (int j = 0; j < jsonData.length(); j++) {
                data.add(jsonData.getInt(j));
            }
            dataSet.add(new ChartDataSet(arr.getJSONObject(i).getString("header"), data));
        }
    }

    /**
     * A method to return a json rep of the graph to be generated
     * @return
     */
    public String toJson(){

        return JsonConverter.serializeObject(this.getGraphType().getJsonCode(), this.getMainTitle(), 
        		this.getxTitle(), this.xAxisLabels, this.getyTitle(), this.dataSet);

    }
}
