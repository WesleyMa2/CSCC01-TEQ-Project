package com.tdat.data.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.JsonConverter;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;

class JsonConverterTest {
    private static TableData tableData = new TableData();
    private static VisitData visit1 = new VisitData();
    private static SingleTableReader tableReader;

    @BeforeAll
    public static void setUp() {
        MasterData.clear();
        
        int[] max = new int[] {5, 3, 7, 4, 1};
        for (int i = 0; i < max.length; i++) {
            
            for (int j = 0; j < max[i]; j++) {
                VisitData data = new VisitData();
                data.addColumnData("Number of Children", (max[i] + 5) + "");
                tableData.addVisitData(data);
            }
        }

        tableReader = new SingleTableReader(tableData);
    }

    @Test
    @DisplayName("Test creating json object using columns entries")
    void jsonColumnEntriesCount() throws ColumnNotFoundException {
        String expected = "{\"data\":{\"8\":3,\"12\":7,\"9\":4,\"6\":1,\"10\":5},\"name\":\"Number of Children\"}";

        Map<String, Integer> data = tableReader.columnEntriesCount("Number of Children");
        String actual = JsonConverter.serializeObject("Number of Children", data);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test creating json object with null object")
    void dneColumnEntries() throws ColumnNotFoundException {
        String expected = "{\"data\":{}}";
        String actual = JsonConverter.serializeObject(null, null);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test creating json object including extra column entries")
    void extraColumnEntriesCount() throws ColumnNotFoundException {
    	String column = "Number of Children";
    	
        visit1.addColumnData("Another Column", "99999");
        tableData.addVisitData(visit1);
        tableReader = new SingleTableReader(tableData);
        
        Map<String, Integer> data = tableReader.columnEntriesCount(column);
        String actual = JsonConverter.serializeObject(column, data);
        
        assertTrue(!actual.contains("99999"));
        assertTrue(!actual.contains("Another Column"));
    }
}