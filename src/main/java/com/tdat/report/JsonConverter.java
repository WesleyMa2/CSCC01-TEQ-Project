package com.tdat.report;

import java.util.List;

import com.tdat.report.chart.ChartDataset;
import org.json.JSONObject;

/**
 * A class to serialize table data into JSON format for printing
 */
public class JsonConverter {
    
    // Current Payload Structure (Keep Updated)
    // {
    //     "type": "line",
    //     "xAxisTitle": "# Of Children",
    //     "xAxisLabels": ["1", "2", "3", "4", "5+"],
    //     "yAxisTitle": "Value",
    //     "dataSet": [
    //         { "header": "2018", "data": [3, 6, 1, 7, 8] },
    //         { "header": "2017", "data": [2, 8, 3, 12, 6] }
    //     ]
    // }

    public static String serializeObject(String type, String xAxisTitle, List<String> xAxisLabels, String yAxisTitle, List<ChartDataset> data) {

        if (type == null) {
            throw new NullPointerException("type is null");
        }

        if (xAxisTitle == null) {
            throw new NullPointerException("xAxisTitle is null");
        }

        if (xAxisLabels == null) {
            throw new NullPointerException("xAxisLabels is null");
        }

        if (xAxisLabels.size() == 0) {
            throw new IllegalStateException("xAxisLabels is empty");
        }

        if (yAxisTitle == null) {
            throw new NullPointerException("yAxisTitle is null");
        }

        if (data == null) {
            throw new NullPointerException("data is null");
        }

    	JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("xAxisTitle", xAxisTitle);
        json.put("xAxisLabels", xAxisLabels);
        json.put("yAxisTitle", yAxisTitle);
        json.put("dataSet", data);

        return json.toString();
    }

// 	Unused method
//    public static HashMap<String, Integer> deserializeObject(String data) {
//        HashMap<String, Integer> result = new HashMap<String, Integer>();
//        Map<String, Object> json = new JSONObject(data).toMap();
//
//        for (Map.Entry<String, Object> entry : json.entrySet()) {
//            if (!(entry.getValue() instanceof Integer)) {
//                System.out.println(entry.getValue() + " error in data, while parsing to integer.");
//                return null;
//            }
//            Integer convert = (Integer) entry.getValue();
//            String key = entry.getKey();
//
//            if (!result.containsKey(key)) {
//                result.put(key, convert);
//            }
//        }
//
//        return result;
//    }

}
