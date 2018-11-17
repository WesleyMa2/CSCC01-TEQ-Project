package com.tdat.data;

import java.time.Year;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class to hold all the raw data uploaded to the system.
 */
public class MasterData {
	
	public static Map<Year, TableData> serviceProvidedData = new HashMap<Year, TableData>();
	public static AtomicInteger reportId = new AtomicInteger();
	public static AtomicInteger publicDataId = new AtomicInteger();

	public static TableData initialVisitData = new TableData();

	public static boolean yearExists(Year year) {
		return serviceProvidedData.containsKey(year);
	}
	
	public static Map<Year, TableData> getServiceProvidedData() {
		return serviceProvidedData;
	}
	
	public static void clear() {
		serviceProvidedData.clear();
	}

	public static TableData getYearData(Year year) {
		return serviceProvidedData.get(year);
	}

	public static void printYearData(Year year){
        TableData yearData = getYearData(year);
        List<String> columns = yearData.getColumnList();
        System.out.println("\n[Data for year of " + year + "]");
        for (String column: columns){
            System.out.println(" '" + column + "':\t" + yearData.getColumnEntries(column));
        }
    }

	public static void setYearData(Year year, TableData yearData) {
		serviceProvidedData.put(year, yearData);
	}

    /**
     * Given a TableData and it's corresponding year,
     * adds that on to the existing table under that year in the master data
     * @param year
     * @param allVisits
     */
	public static void setYearData(Year year, List<Map<String, String>> allVisits) {
		TableData existingYearData;
		if(yearExists(year)) {
			existingYearData = serviceProvidedData.get(year);
		} else {
			existingYearData = new TableData();
		}
		for (Map<String,String> visitKeyValue: allVisits){
		    VisitData visitToAdd = new VisitData();
		    visitToAdd.setData(visitKeyValue);
		    existingYearData.addVisitData(visitToAdd);
        }
		
		serviceProvidedData.put(year, existingYearData);
	}

	public static List<TableData> getRangeOfYear(Year startYear, Year endYear){
		List<TableData> subRange = new ArrayList<TableData>();
		for (Year year: serviceProvidedData.keySet()) {
			if (year.compareTo(startYear) > 0 && year.compareTo(endYear) < 0){
				subRange.add(serviceProvidedData.get(year));
			}
		}
		return subRange;
	}
	
	/*
	 * Returns all the columns in serviceProvidedData without any duplicates
	 */
	public static List<String> getAllColumns() {
		// Set empty list of all columns in serviceProvidedData
		List<String> allColumns = new ArrayList<String>();
		
		// Loop through each table
		for (TableData table: serviceProvidedData.values()) {
			// Get the list of columns in the current table
			List<String> columnsInTable = table.getColumnList();
			// Loop through each column in the current table
			for (int eachColumn = 0; eachColumn < columnsInTable.size(); eachColumn++) {
				// If the current column is not in the list of all columns, then add it
				if(!allColumns.contains(columnsInTable.get(eachColumn))) {
					allColumns.add(columnsInTable.get(eachColumn));
				}
			}
		}
		return allColumns;
	}
}