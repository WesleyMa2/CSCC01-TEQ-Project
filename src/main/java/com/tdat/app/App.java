package com.tdat.app;

import com.tdat.gui.GUI;
import java.io.File;
import java.time.Year;
import java.util.ArrayList;

public class App {
	public static File selectedFile;
    public static Year selectedYear;
	public static String selectedFileType;
	

	public static void main(String[] args) {
	    System.out.println("This is where the everything happens. -Alvin");
        GUI.main(null);
	}
	
}
