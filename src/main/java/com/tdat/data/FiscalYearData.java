package com.tdat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to hold all entries (rows) entered within a given year.
 */
public class FiscalYearData {
	
	public List<VisitData> visitData = new ArrayList<PersonData>();

	public FiscalYearData() { }

	public List<VisitData> getPeopleData() {
		return visitData;
	}

	public void setPeopleData(List<PersonData> peopleData) {
		this.visitData = peopleData;
	}

	public void addPersonData(VisitData personData) {
		visitData.add(personData);
	}
}
