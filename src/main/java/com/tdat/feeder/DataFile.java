package com.tdat.feeder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface DataFile {
	
	/*
	 * Converts the file at fileLocation to a list with each item being a delivered service
	 * and each delivered service having specific details in hash maps.
	 * @param fileLocation The location of a file in the hard drive.
	 * @returns ArrayList<HashMap<String, String>> A list of delivered services.
	 */
	public ArrayList<HashMap<String, String>> converter(String fileLocation) throws FileNotFoundException, IOException;
	
}
