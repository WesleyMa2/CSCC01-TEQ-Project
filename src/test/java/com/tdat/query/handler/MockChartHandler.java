package com.tdat.query.handler;

import com.tdat.query.InvalidQueryException;

public class MockChartHandler extends ChartHandler {

  public MockChartHandler() {
    super("mock");
  }

  @Override
  public String handle(String[] arguments) throws InvalidQueryException {
    return "test";
  }
}
