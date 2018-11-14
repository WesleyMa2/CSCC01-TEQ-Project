package com.tdat.gui.reports;

import com.tdat.app.App;
import com.tdat.query.CommandHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TDATQLListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // Make's sure the query isn't empty
    String query = ReportsPanel.getTdatqlQuery().getText().trim();
    if (query.length() < 1){
      System.out.println("Error: query is empty");
      return;
    }

    if (CommandHandler.handle(query)) {
      String[] row = {Integer.toString(App.reportsList.size()),
          App.reportsList.get(App.reportsList.size() - 1).getMainTitle(),
          App.reportsList.get(App.reportsList.size() - 1).getGraphType().getjsonCode()
      };
      ReportsPanel.tableModel.addRow(row);
    }


  }

}
