package com.tdat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to hold all entries (rows) entered within a given year.
 */
public class TableData {
	
	private List<VisitData> allVisitsData = new ArrayList<VisitData>();

	public TableData() { }

	public List<VisitData> getVisitsData() {
		return allVisitsData;
	}

	public void Data(List<VisitData> data) {
		this.allVisitsData = data;
	}

	public void addVisitData(VisitData data) {
		allVisitsData.add(data);
	}
}
