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
	    String result = year.toString() + ":\n";
	    TableData currYear = getYearData(year);
	    List<VisitData> visitData = currYear.getVisitsData();
	    Set currCols = ((VisitData) visitData.get(0)).getData().keySet();
        result += currCols.toString() + "\n";
        System.out.println(result);
    }

	public static void setYearData(Year year, TableData yearData) {
		allData.put(year, yearData);
	}
	
	public static void setYearData(Year year, List<Map<String, String>> allVisits) {
		TableData yearData;
		if(yearExists(year)) {
			yearData = allData.get(year);
		} else {
			yearData = new TableData();
		}
		
		for(int a = 0; a < allVisits.size(); a++) {
			VisitData visitData = new VisitData();
			for(String key: allVisits.get(a).keySet()) {
				visitData.addColumnData(key, allVisits.get(a).get(key));
			}
			yearData.addVisitData(visitData);
		}
		
		allData.put(year, yearData);
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