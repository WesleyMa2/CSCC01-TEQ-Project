package com.tdat.data;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent an individual's entry (row) on a iCare spreadsheet
 */
public class VisitData {
	private Map<String, String> data = new HashMap<String, String>();

	public VisitData(){ }

	public Map<String, String> getData() {
		return data;
	}

	public String getColumnData(String columnName) {
		return this.data.get(columnName);
	}

	public void addColumnData(String columnName, String value) {
		this.data.put(columnName, value);
	}
	
	public boolean columnDataExists(String columnName) {
		boolean exists = true;
		
		if(this.data.get(columnName) == null) {
			exists = false;
		}
		
		return exists;
	}
}
