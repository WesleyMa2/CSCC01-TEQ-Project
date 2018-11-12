package com.tdat.app;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.tdat.gui.MainWindow;
import com.tdat.gui.publicData.PublicDataCache;
import com.tdat.report.chart.ChartScheme;

public class App {
	public static String EMPTY = "N/A";
	public static File selectedFile;
	public static Year selectedYear = Year.of(2018);
	public static String selectedFileType = ".xlsx";
	public static HashMap<String, File> fileUploadDict = new HashMap<String, File>();
	public static ArrayList<ChartScheme> reportsList = new ArrayList<ChartScheme>();
	public static String appTitle = "TEQ Data Aggregation Tool (TDAT)";

	public static void main(String[] args) {

		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		PublicDataCache.init();

		new MainWindow();
	}

}
