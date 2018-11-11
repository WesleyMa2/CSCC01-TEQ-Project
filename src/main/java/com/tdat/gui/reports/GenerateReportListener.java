package com.tdat.gui.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tdat.app.App;
import com.tdat.report.ChartJS;

public class GenerateReportListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String json = App.reportsList.get(0).toJson();
		ChartJS.create(json);
	}

}
