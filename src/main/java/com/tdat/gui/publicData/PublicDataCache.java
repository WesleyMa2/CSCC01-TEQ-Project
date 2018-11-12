package com.tdat.gui.publicData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.JSONString;

/**
 * A class to serialize table data into JSON format for printing
 */
public class PublicDataCache {

    public static HashMap<String, JSONObject> CachedPublicData = new HashMap<String, JSONObject>();

    public static void init() {

        Path p = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "publicData");

        if (!Files.exists(p)) {
            System.out.println("Did not find public data");
            return;
        }
        File[] listOfFiles = p.toFile().listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                BufferedReader reader = null;

                try {
                    reader = new BufferedReader(new FileReader(file));
                    String line =  reader.lines().collect(Collectors.joining());
                    JSONObject json = new JSONObject(line);

                    CachedPublicData.put(json.getString("mainTitle"), json);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println(CachedPublicData);
    }
}
