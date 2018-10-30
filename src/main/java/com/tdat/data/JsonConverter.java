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

    public static Map<String, Object> DeserializeObject(String data) {
        return new JSONObject(data).toMap();
    }

}
