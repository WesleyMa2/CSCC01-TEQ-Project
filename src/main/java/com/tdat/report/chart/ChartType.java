package com.tdat.report.chart;

public enum ChartType {
  LINE("line"), BAR("bar"), TABLE("table");

  private String jsonCode;

  ChartType(String jsonCode) {
    this.jsonCode = jsonCode;
  }

  public String getjsonCode() {
    return jsonCode;
  }
}
