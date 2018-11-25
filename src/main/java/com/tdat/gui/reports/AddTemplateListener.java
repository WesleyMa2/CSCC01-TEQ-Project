package com.tdat.gui.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.tdat.report.chart.templates.Template;
import com.tdat.report.chart.templates.TemplateRepository;

public class AddTemplateListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Template chosenTemplate = TemplateRepository.templates.get(ReportsPanel.templatesDropdown.getSelectedIndex());
		if(chosenTemplate.usable()) {
			new AddTemplateWindow(chosenTemplate);
		} else {
			JOptionPane.showMessageDialog(null, "The template you have selected is unusable. It may be because "
					+ "you have not uploaded all the necessary information yet.",
					"Alert", JOptionPane.WARNING_MESSAGE);    
		}
	}

}
