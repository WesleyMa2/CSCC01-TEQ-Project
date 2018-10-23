package com.tdat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to hold all entries (rows) entered within a given year.
 */
public class FiscalYearData {
	
	public List<VisitData> allVisitsData = new ArrayList<VisitData>();

	public FiscalYearData() { }

	public List<VisitData> getPeopleData() {
		return allVisitsData;
	}

	public void setPeopleData(List<VisitData> peopleData) {
		this.allVisitsData = peopleData;
	}

	public void addVisitData(VisitData visitData) {
		allVisitsData.add(visitData);
	}
}
