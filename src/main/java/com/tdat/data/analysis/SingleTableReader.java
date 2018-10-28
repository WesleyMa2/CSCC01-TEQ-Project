package com.tdat.data.analysis;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @return a Map<entry, count>
     */
    public Map<String, Integer> columnEntriesCount(String column) throws ColumnNotFoundException {
        Map<String, Integer> columnEntriesData = new HashMap<>();

        for (VisitData singleVisit: visitDataList){
            String entry = singleVisit.getColumnData(column);
            if (!listAllColumns().contains(column)){
                throw new ColumnNotFoundException(column);
            }
            if (!columnEntriesData.containsKey(column)){
                columnEntriesData.put(column, 1);
            }else {
                columnEntriesData.put(column, columnEntriesData.get(column)+1);
            }
        }
        return columnEntriesData;
    }

    public List<Map<String, Integer>> multiColumnEntriesCount(List<String> columnsList) throws ColumnNotFoundException {
        List<Map<String, Integer>> result = new ArrayList<>();
        for (String column: columnsList){
            result.add(columnEntriesCount(column));
        }
        return result;
    }

    public Integer getNumEntries(){
        return visitDataList.size();
    }

    public List<String> listAllColumns(){
        return (List<String>) visitDataList.get(0).getData().keySet();
    }

    public Integer getNumColumns(){
        return listAllColumns().size();
    }
}
