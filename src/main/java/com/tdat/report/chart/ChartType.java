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

  public String getjsonCode() {
    return jsonCode;
  }
}
