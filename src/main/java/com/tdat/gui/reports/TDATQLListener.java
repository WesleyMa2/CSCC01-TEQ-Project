package com.tdat.gui.reports;

import com.tdat.app.App;
import com.tdat.query.CommandHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TDATQLListener implements ActionListener {

	private String uncleanedQuery;
	
	public TDATQLListener(String query) {
		this.uncleanedQuery = query;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		CommandHandler.handle(uncleanedQuery);
	}

}
