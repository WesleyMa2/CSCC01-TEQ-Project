package com.tdat.data;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

	public static void setYearData(Year year, FiscalYearData yearData) {
		allData.put(year, yearData);
	}
	
	public static void setYearData(Year year, ArrayList<HashMap<String, String>> allVisits) {
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