package com.tdat.report.chart;

/**
 * An enum to hold the different types of charts that can be generated
 */
public enum ChartType {
  LINE("line"), BAR("bar"), TABLE("table");

  private String jsonCode;

  ChartType(String jsonCode) {
    this.jsonCode = jsonCode;
  }

  public static ChartType getChartTypeFromString(String type) {
    return ChartType.valueOf(type.toUpperCase());
  }

  public String getJsonCode() {
    return jsonCode;
  }

  public String getPrettyJsonCode() {
    String s1 = jsonCode.substring(0, 1).toUpperCase();
    return s1 + jsonCode.substring(1);
  }
}
