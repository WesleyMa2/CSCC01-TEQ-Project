package com.tdat.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tdat.report.chart.ChartDataSet;
import com.tdat.data.ColumnNotFoundException;
import com.tdat.report.JsonConverter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonConverterTest {

    @Test
    @DisplayName("Test creating json object using entries")
    void jsonColumnEntriesCount() throws ColumnNotFoundException {
        List<String> labels = Arrays.asList("label1", "label2", "label3");

        String expected = "{\"xAxisLabels\":[\"label1\",\"label2\",\"label3\"],\"mainTitle\":\"main title\",\"xAxisTitle\":\"x axis title\",\"type\":\"type of chart\",\"dataSet\":[{\"data\":[1,2,3,4],\"header\":\"a header\"}],\"yAxisTitle\":\"y axis title\"}";

        List<ChartDataSet> data = new ArrayList<>(Arrays.asList(new ChartDataSet("a header", Arrays.asList(1,2,3,4))));

        String actual = JsonConverter.serializeObject("type of chart", "main title", "x axis title", labels, "y axis title", data);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test creating json object with null input")
    void dneColumnEntries() throws NullPointerException, IllegalStateException {

        assertThrows(NullPointerException.class, () -> { JsonConverter.serializeObject(null, null, null, null, null, null); });

        assertThrows(NullPointerException.class, () -> { JsonConverter.serializeObject("a type", null, null, null, null, null); });

        assertThrows(NullPointerException.class, () -> { JsonConverter.serializeObject("a type", "main title", null, null, null, null); });
        
        assertThrows(NullPointerException.class, () -> { JsonConverter.serializeObject("a type", "main title", "x axis title", null, null, null); });

        assertThrows(IllegalStateException.class, () -> { JsonConverter.serializeObject("a type", "main title", "x axis title", new ArrayList<String>(), null, null); });

        assertThrows(NullPointerException.class, () -> { JsonConverter.serializeObject("a type", "main title", "x axis title", Arrays.asList("x axis label"), null, null); });

        assertThrows(NullPointerException.class, () -> { JsonConverter.serializeObject("a type", "main title", "x axis title", Arrays.asList("x axis label"), "y axis title", null); });
    }
}