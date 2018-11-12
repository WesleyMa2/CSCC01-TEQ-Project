package com.tdat.query.handler;

import com.tdat.query.InvalidQueryException;
import com.tdat.report.chart.DistributionChartScheme;

/**
 *  A method to create a DistributionChartScheme in json, based off user query
 */
public class DistributionHandler extends ChartHandler {

  public DistributionHandler() {
    super("distribution");
  }

  @Override
  public String handle(String[] arguments) throws InvalidQueryException {
    int columnIndex = checkForKey(arguments, "of");
    if(columnIndex == -1){
      throw new InvalidQueryException();
    }
    System.out.println("Creating a Distribution chart:");
    System.out.println("[Column]: " + arguments[columnIndex]);
    DistributionChartScheme result = new DistributionChartScheme(arguments[columnIndex],
        getChartType(arguments));
    result.setMainTitle(getTitle(arguments)).setXTitle(getXTitle(arguments))
        .setYTitle(getYTitle(arguments));
    return result.toJson();
  }
}
