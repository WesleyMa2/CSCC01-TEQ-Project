package com.tdat.gui.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tdat.app.App;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;
import com.tdat.report.chart.DistributionChartScheme;

public class AddDistributionReportListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		new AddDistributionReportWindow();
	}

}
