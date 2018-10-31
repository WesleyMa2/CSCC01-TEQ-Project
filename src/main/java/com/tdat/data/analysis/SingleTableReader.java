package com.tdat.data.analysis;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.TableData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides various measures of data on a given table
 */
public class SingleTableReader {
    private TableData tableData;

    public SingleTableReader(TableData tableData){this.tableData = tableData;}


    /**
     * Counts the #of times each entry occurs for a given column
     * @param column Name of the column
     * @return a map(entry: count)
     */
    public Map<String, Integer> columnEntriesCount(String column) throws ColumnNotFoundException {
        Map<String, Integer> columnEntriesData = new HashMap<>();
      
        if (!tableData.getColumnList().contains(column)) {
            throw new ColumnNotFoundException(column);
        }
        List<String> allColumnEntries = tableData.getColumnEntries(column);
        for (String entry: allColumnEntries){
            if (!columnEntriesData.containsKey(entry)){
                columnEntriesData.put(entry, 1);
            } else {
                columnEntriesData.put(entry, columnEntriesData.get(entry) + 1);
            }
        }
        return columnEntriesData;
    }

    /**
     * Counts up the entries for a list of columns
     * @param columnsList
     * @return a map(columnName: map(entry: count))
     * @throws ColumnNotFoundException
     */
    public Map<String,Map<String, Integer>> multiColumnEntriesCount(List<String> columnsList) throws ColumnNotFoundException {
        Map<String, Map<String, Integer>> result = new HashMap<>();
        
        for (String column: columnsList) {
            result.put(column, columnEntriesCount(column));
        }
        return result;
    }

    public int getNumEntries(){
        return tableData.getVisitsData().size();
    }


    public int getNumColumns(){
        return tableData.getColumnList().size();
    }
}
