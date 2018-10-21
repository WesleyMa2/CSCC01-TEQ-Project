package data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to hold all entries (rows) entered within a given year.
 */
public class FiscalYearData {
  public List<PersonData> peopleData = new ArrayList<>();

  public FiscalYearData() { }

  public List<PersonData> getPeopleData() {
    return peopleData;
  }

  public void setPeopleData(List<PersonData> peopleData) {
    this.peopleData = peopleData;
  }

  public void addPersonData(PersonData personData) {
    peopleData.add(personData);
  }
}
