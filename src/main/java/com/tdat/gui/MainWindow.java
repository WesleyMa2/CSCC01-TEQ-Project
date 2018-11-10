package com.tdat.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.tdat.app.App;

/*
 * This is the window that holds all major views. These views include these panels: MainPanel, UploadPanel and ReportsPanel.
 */
public class MainWindow {
	
	private final JFrame frame;
	
	public MainWindow() {
		frame = new JFrame(App.appTitle);
		Dimension windowMinSize = new Dimension(600, 500);
		frame.setMinimumSize(windowMinSize);
		frame.setSize(windowMinSize);
		frame.setResizable(true);

		JTabbedPane tabbedPane = new JTabbedPane();
		
		MainPanel mainPanel = new MainPanel();
        tabbedPane.addTab(mainPanel.getPanelTitle(), mainPanel);
        
        UploadPanel uploadPanel = new UploadPanel();
        tabbedPane.addTab(uploadPanel.getPanelTitle(), uploadPanel);
        
        ReportsPanel reportsPanel = new ReportsPanel();
        tabbedPane.addTab(reportsPanel.getPanelTitle(), reportsPanel);
        
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        frame.add(tabbedPane);
        frame.setVisible(true);
	}
	
}
