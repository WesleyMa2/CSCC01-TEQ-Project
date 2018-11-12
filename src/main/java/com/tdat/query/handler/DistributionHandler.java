package com.tdat.query.handler;

import com.tdat.query.InvalidQueryException;
import com.tdat.report.chart.DistributionChartScheme;

/**
 *  A method to create a DistributionChartScheme in json, based off user query
 */
public class DistributionHandler extends ChartHandler {

  public DistributionHandler() {
    super("Distribution");
  }

  @Override
  public String handle(String[] arguments) throws InvalidQueryException {
    DistributionChartScheme result = new DistributionChartScheme(arguments[0],
        getChartType(arguments));
    result.setMainTitle(getTitle(arguments)).setXTitle(getXTitle(arguments))
        .setYTitle(getTitle(arguments));
    return result.toJson();
  }
}
