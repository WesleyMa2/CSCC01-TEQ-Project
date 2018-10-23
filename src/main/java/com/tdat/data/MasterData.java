package com.tdat.data;

import java.time.Year;
import java.util.*;
import java.util.Map.Entry;

/**
 * A class to hold all the raw data uploaded to the system.
 */
public class MasterData {
	
	public static Map<Year, FiscalYearData> allData = new HashMap<Year, FiscalYearData>();

	private static boolean yearExists(Year year) {
		return allData.containsKey(year);
	}
	
	public static Map<Year, FiscalYearData> getAllData() {
		return allData;
	}

	public static FiscalYearData getYearData(Year year) {
		return allData.get(year);
	}

	public static void printYearData(Year year){
	    String result = year.toString() + ":\n";
	    FiscalYearData currYear = getYearData(year);
	    List<VisitData> visitData = currYear.getVisitsData();
	    Set currCols = ((VisitData) visitData.get(0)).getData().keySet();
        result += currCols.toString() + "\n";
        System.out.println(result);
    }

	public static void setYearData(Year year, FiscalYearData yearData) {
		allData.put(year, yearData);
	}
	
	public static void setYearData(Year year, List<Map<String, String>> allVisits) {
		FiscalYearData yearData;
		if(yearExists(year)) {
			yearData = allData.get(year);
		} else {
			yearData = new FiscalYearData();
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

	public static List<FiscalYearData> getRangeOfYear(Year startYear, Year endYear){
		List<FiscalYearData> subRange = new ArrayList<FiscalYearData>();
		for (Year year: allData.keySet()) {
			if (year.compareTo(startYear) > 0 && year.compareTo(endYear) < 0){
				subRange.add(allData.get(year));
			}
		}
		return subRange;
	}
}