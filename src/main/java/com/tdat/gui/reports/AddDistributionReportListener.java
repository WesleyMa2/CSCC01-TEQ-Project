package com.tdat.gui.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDistributionReportListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		new AddChartWindow("Distribution");
	}

}
