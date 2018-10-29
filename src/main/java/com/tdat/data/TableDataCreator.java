package com.tdat.data;

import java.time.Year;
import java.util.ArrayList;

public class TableDataCreator {
	
	/*
	 * Creates a custom TableData object from the MasterData object using a list of column names and years.
	 * @param master The master data object.
	 * @param columns The list of columns to customize the returned TableData object.
	 * @param years The list of years to customize the returned TableData object.
	 */
	public static TableData customize(MasterData master, ArrayList<String> columns, ArrayList<Year> years) throws YearNotFoundException {

		TableData custom = new TableData();
		
		// Loops through all the years required
		for(int indexYearList = 0; indexYearList < years.size(); indexYearList++) {
			// Throw a YearNotFoundException if year is not listed in master
			if(!MasterData.yearExists(years.get(indexYearList))) {
				throw new YearNotFoundException(years.get(indexYearList).toString());
			} else {
				TableData currentYearData = MasterData.getYearData(years.get(indexYearList));
				
				ArrayList<VisitData> allCurrentVisitData = (ArrayList<VisitData>) currentYearData.getVisitsData();
				int hold = allCurrentVisitData.size();
				for(int indexVisitDataList = 0; indexVisitDataList < hold; indexVisitDataList++) {
					// Getting the visit data
					VisitData currentVisitData = allCurrentVisitData.get(indexVisitDataList);
					// New visit data
					VisitData newVisitData = new VisitData();
					// Loop through all the columns in the visit data
					for(int indexColumnsList = 0; indexColumnsList < columns.size(); indexColumnsList++) {
						// If the column specified in the parameters is found, add its value to the new visit data
						if(currentVisitData.columnDataExists(columns.get(indexColumnsList))) {
							newVisitData.addColumnData(columns.get(indexColumnsList), currentVisitData.getColumnData(columns.get(indexColumnsList)));
						}
					}
					// Add the visit data to the table
					currentYearData.addVisitData(newVisitData);
				}
			}
		}
		
		return custom;
		
	}
	
}
