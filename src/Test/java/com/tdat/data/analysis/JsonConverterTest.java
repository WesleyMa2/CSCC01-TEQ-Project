package com.tdat.data.analysis;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.JsonConverter;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {
    private static TableData tableData = new TableData();
    private static VisitData visit1 = new VisitData();
    private static SingleTableReader tableReader;
    @BeforeAll
    public static void setUp() {
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
        String expected = "{\"8\":3,\"12\":7,\"9\":4,\"6\":1,\"10\":5}";

        Map<String, Integer> data = tableReader.columnEntriesCount("Number of Children");
        String actual = JsonConverter.SerializeObject(data);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test creating json object with null object")
    void dneColumnEntries() throws ColumnNotFoundException {
        String expected = "{}";
        String actual = JsonConverter.SerializeObject(null);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test creating null json object into hashmap object")
    void emptyColumnEntries() throws ColumnNotFoundException {
        HashMap<String, Integer> expected = new HashMap<String, Integer>();
        HashMap<String, Integer> actual = JsonConverter.DeserializeObject("{}");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test creating json object including extra column entries")
    void extraColumnEntriesCount() throws ColumnNotFoundException {
        String expected = "{\"8\":3,\"12\":7,\"9\":4,\"6\":1,\"10\":5}";

        visit1.addColumnData("Another Column", "99999");
        tableData.addVisitData(visit1);

        Map<String, Integer> data = tableReader.columnEntriesCount("Number of Children");
        String actual = JsonConverter.SerializeObject(data);
        assertTrue(!actual.contains("99999"));
        assertTrue(!actual.contains("Another Column"));
    }


    @Test
    @DisplayName("Test creating hashamp from a json object")
    void deserializeColumns() throws ColumnNotFoundException {
        Map<String, Integer> expected = tableReader.columnEntriesCount("Number of Children");
        HashMap<String, Integer> actual = JsonConverter.DeserializeObject("{\"8\":3,\"12\":7,\"9\":4,\"6\":1,\"10\":5}");
        
        assertEquals(expected, actual);
    }
}