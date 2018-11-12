package com.tdat.query;

import com.tdat.query.handler.DistributionHandler;
import com.tdat.query.handler.Handler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to handle a given query from the user to create a graph
 */
public class CommandHandler {

  private static Map<String, Handler> handlers = new HashMap<>();

  /**
   * Add new handlers here
   */
  public void setupHandlers() {
    registerHandler(new DistributionHandler());
  }


  /**
   * A method to add new handlers to the map of registered commands
   */
  private void registerHandler(Handler newHandler) {
    handlers.put(newHandler.getKeyword(), newHandler);
  }

  public static String handle(String input) {
    String[] splitInput = input.toLowerCase().split("\\s");
    String command = splitInput[0];
    String[] args = Arrays.copyOfRange(splitInput, 1, splitInput.length);

    try {
      if (handlers.containsKey(command.toLowerCase())) {
        return handlers.get(command).handle(args);
      }
      throw new InvalidQueryException();
    } catch (InvalidQueryException e) {
      System.out.println("The query has syntax errors");
      return null;
    }
  }

}
