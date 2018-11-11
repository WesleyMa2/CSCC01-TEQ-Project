package com.tdat.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.tdat.app.App;
import com.tdat.gui.reports.ReportsPanel;

/*
 * This is the window that holds all major views. These views include these panels: MainPanel, UploadPanel and ReportsPanel.
 */
public class MainWindow {
	
	private final JFrame frame;
	
	public MainWindow() {
		frame = new JFrame(App.appTitle);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setResizable(true);

		JTabbedPane tabbedPane = new JTabbedPane();
		
		MainPanel mainPanel = new MainPanel();
        tabbedPane.addTab(mainPanel.getPanelTitle(), mainPanel);
        
        UploadPanel uploadPanel = new UploadPanel();
        tabbedPane.addTab(uploadPanel.getPanelTitle(), uploadPanel);
        
        ReportsPanel reportsPanel = new ReportsPanel();
        tabbedPane.addTab(reportsPanel.getPanelTitle(), reportsPanel);
        
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        JScrollPane scrollPane = new JScrollPane(tabbedPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
}
