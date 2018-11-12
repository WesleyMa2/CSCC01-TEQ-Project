package com.tdat.gui.reports;

import com.tdat.app.App;
import com.tdat.query.CommandHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TDATQLListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		CommandHandler.handle(ReportsPanel.getTdatqlQuery().getText());
	}

}
