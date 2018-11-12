package com.tdat.query.handler;

import com.tdat.query.InvalidQueryException;

public interface Handler {

  public String getKeyword();

  public String handle(String[] args) throws InvalidQueryException;
}
