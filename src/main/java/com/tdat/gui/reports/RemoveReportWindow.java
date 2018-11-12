package com.tdat.gui.reports;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tdat.app.App;

public class RemoveReportWindow {

	private final JFrame frame;
	
	public RemoveReportWindow() {
		frame = new JFrame(App.appTitle);
		
		String prompt = "<html>Enter the report number to have it removed. "
				+ "<b>Note that this option cannot be undone.</b></html>";
		Boolean removed = false;
		Boolean canceled = false;
		
		while(!removed && !canceled) {
			try {
				String indexReport = JOptionPane.showInputDialog(frame, prompt);
				if(indexReport != null) {
					App.reportsList.remove(Integer.parseInt(indexReport) - 1);
					removed = true;
					String tr[][] = new String[App.reportsList.size()][3];
					for(int index = 0; index < App.reportsList.size(); index++) {
						tr[index][0] = (Integer.toString(index+1));
						tr[index][1] = App.reportsList.get(index).getMainTitle();
						tr[index][2] = App.reportsList.get(index).getGraphType().getjsonCode();
					} 
					String th[] = {"Report Menu", "Report Title","Type of Report"}; 
					ReportsPanel.tableModel.setDataVector(tr, th);
				} else {
					canceled = true;
				}
			} catch(NumberFormatException e) {
				prompt = "<html><b>Input was not a number.</b> Enter a valid report number to have it removed.</html>";
			} catch(Exception e) {
				prompt = "<html><b>Error occured.</b> Enter a valid report number to have it removed.</html>";
			}
		}
	}
	
}
