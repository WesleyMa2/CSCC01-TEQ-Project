package com.tdat.data;

import java.time.Year;
import java.util.*;

/**
 * A class to hold all the raw data uploaded to the system.
 */
public class MasterData {
	
	public static Map<Year, TableData> allData = new HashMap<Year, TableData>();

	public static boolean yearExists(Year year) {
		return allData.containsKey(year);
	}
	
	public static Map<Year, TableData> getAllData() {
		return allData;
	}

	public static TableData getYearData(Year year) {
		return allData.get(year);
	}

	public static void printYearData(Year year){
//	    String result = year.toString() + ":\n";
//	    TableData currYear = getYearData(year);
//	    List<VisitData> visitData = currYear.getVisitsData();
//	    Set currCols = ((VisitData) visitData.get(0)).getData().keySet();
//        result += currCols.toString() + "\n";
//        System.out.println(result);
        TableData yearData = getYearData(year);
        List<String> columns = yearData.getColumnList();
        System.out.println("Data for year of " + year);
        for (String column: columns){
            System.out.println("[" + column + "]:\t" + yearData.getColumnEntries(column));
        }

    }

	public static void setYearData(Year year, TableData yearData) {
		allData.put(year, yearData);
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
			existingYearData = allData.get(year);
		} else {
			existingYearData = new TableData();
		}
		for (Map<String,String> visitKeyValue: allVisits){
		    VisitData visitToAdd = new VisitData();
		    visitToAdd.setData(visitKeyValue);
		    existingYearData.addVisitData(visitToAdd);
        }
		
		allData.put(year, existingYearData);
	}

	public static List<TableData> getRangeOfYear(Year startYear, Year endYear){
		List<TableData> subRange = new ArrayList<TableData>();
		for (Year year: allData.keySet()) {
			if (year.compareTo(startYear) > 0 && year.compareTo(endYear) < 0){
				subRange.add(allData.get(year));
			}
		}
		return subRange;
	}
}