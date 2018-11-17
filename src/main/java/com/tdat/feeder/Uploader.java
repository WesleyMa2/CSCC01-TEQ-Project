package com.tdat.feeder;

import java.io.File;
import java.io.IOException;
import java.time.Year;

import com.tdat.app.App;
import com.tdat.data.MasterData;

import java.util.List;
import java.util.Map;

public class Uploader {

	/*
	 * Uploads the file
	 * @param fileLocation The location of a file in the hard drive.
	 * @returns ArrayList<HashMap<String, String>> A list of delivered services.
	 */
	public static boolean upload(Year year, File file) {
	    DataFileReader fileReader;
		try {
			switch (App.selectedFileType){
                default:
                    fileReader = new XLSXDataFileReader();
			}
			// All the data converted inside
			List<Map<String, String>> allVisits = fileReader.converter(file);
			
			MasterData.setServiceProvidedData(year, allVisits);
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
}
