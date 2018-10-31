package com.tdat.data;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * A class to serialize table data into JSON format for printing
 */
public class JsonConverter {
    
    public static String SerializeObject(Map<String, Integer> data) {
        return new JSONObject(data).toString();
    }

    public static HashMap<String, Integer> DeserializeObject(String data) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        Map<String, Object> json = new JSONObject(data).toMap();

        for (Map.Entry<String, Object> entry : json.entrySet()) {
            if (!(entry.getValue() instanceof Integer)) {
                System.out.println(entry.getValue() + " error in data, while parsing to integer.");
                return null;
            }
            Integer convert = (Integer) entry.getValue();
            String key = entry.getKey();

            if (!result.containsKey(key)) {
                result.put(key, convert);
            }
        }

        return result;
    }

}
