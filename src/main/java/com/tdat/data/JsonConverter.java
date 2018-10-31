package com.tdat.data;

import java.util.Map;

import org.json.JSONObject;

/**
 * A class to serialize table data into JSON format for printing
 */
public class JsonConverter {
    
    public static String serializeObject(String column, Map<String, Integer> mapData) {
    	JSONObject json = new JSONObject();
    	json.put("name", column);
        json.put("data", new JSONObject(mapData));

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
