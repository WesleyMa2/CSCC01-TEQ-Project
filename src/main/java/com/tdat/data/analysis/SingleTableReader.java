package com.tdat.data.analysis;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides various measures of data on a given table
 */
public class SingleTableReader {
    private List<VisitData> visitDataList;

    public SingleTableReader(TableData tableData){
        this.visitDataList = tableData.getVisitsData();
    }

    /**
     * Counts the #of times each entry occurs for a given column
     * @param column Name of the column
     * @return a map(entry: count)
     */
    public Map<String, Integer> columnEntriesCount(String column) throws ColumnNotFoundException {
        Map<String, Integer> columnEntriesData = new HashMap<>();

        for (VisitData singleVisit: visitDataList){
            String entry = singleVisit.getColumnData(column);
            if (!listAllColumns().contains(column)){
                throw new ColumnNotFoundException(column);
            }
            if (!columnEntriesData.containsKey(entry)){
                columnEntriesData.put(entry, 1);
            }else {
                columnEntriesData.put(entry, columnEntriesData.get(entry)+1);
            }
        }
        return columnEntriesData;
    }

    /**
     * Counts up the entries for a list of columns
     * @param columnsList
     * @return a map(column: map(entry: count))
     * @throws ColumnNotFoundException
     */
    public Map<String,Map<String, Integer>> multiColumnEntriesCount(List<String> columnsList) throws ColumnNotFoundException {
        Map<String, Map<String, Integer>> result = new HashMap<>();
        for (String column: columnsList){
            result.put(column, columnEntriesCount(column));
        }
        return result;
    }

    public int getNumEntries(){
        return visitDataList.size();
    }

    public Set<String> listAllColumns(){
        return visitDataList.get(0).getData().keySet();
    }

    public int getNumColumns(){
        return listAllColumns().size();
    }
}
