package com.tdat.feeder;

import java.io.IOException;
import java.time.Year;
import com.tdat.data.MasterData;
import java.util.ArrayList;
import java.util.HashMap;

public class Uploader {

	/*
	 * Uploads the file
	 * @param fileLocation The location of a file in the hard drive.
	 * @returns ArrayList<HashMap<String, String>> A list of delivered services.
	 */
	public static boolean uploader(Year year, DataFile file, String fileLocation, MasterData master) {
		try {
			
			// All the data converted inside
			ArrayList<HashMap<String, String>> services = file.converter(fileLocation);
			
			/*
			// If year already exists the the master data
			if(MasterData.yearExists(year)) {
				
				MasterData.setYearData(year, fiscalyeardata);
			} else {
				MasterData.put(y)
			}
			*/
			
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
}
