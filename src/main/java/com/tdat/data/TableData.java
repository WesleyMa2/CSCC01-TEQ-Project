package com.tdat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to hold all entries (rows) entered within a given year.
 */
public class TableData {
	
	public List<VisitData> allVisitsData = new ArrayList<VisitData>();

	public TableData() { }

	public List<VisitData> getVisitsData() {
		return allVisitsData;
	}

	public void Data(List<VisitData> peopleData) {
		this.allVisitsData = peopleData;
	}

	public void addVisitData(VisitData visitData) {
		allVisitsData.add(visitData);
	}
}
