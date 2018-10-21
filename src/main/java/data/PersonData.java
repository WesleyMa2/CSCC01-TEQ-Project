package data;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent an individual's entry (row) on a iCare spreadsheet
 */
public class PersonData {
  public Map<String, String> data = new HashMap<>();

  public PersonData(){ }

  public Map<String, String> getData() {
    return data;
  }

  public String getColumnData(String columnName) {
    return this.data.get(columnName);
  }

  public void addColumnData(String columnName, String value) {
    this.data.put(columnName, value);
  }
}
