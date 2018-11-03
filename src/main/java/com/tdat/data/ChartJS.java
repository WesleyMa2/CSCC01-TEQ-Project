package com.tdat.data;

import java.nio.ByteBuffer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 * A class to represent a each type of
 */
public class ChartJS {

    private static String API_URL = "http://localhost:3000/";

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    } 

    public static String create(String json) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Content-type", "application/json");
            StringEntity params = new StringEntity(json);
            post.setEntity(params);
        
            HttpResponse response = httpClient.execute(post);
            InputStream in = response.getEntity().getContent();

            Path directoryPath = Paths.get(System.getProperty("user.dir"), "reports");
            Path filePath = Paths.get(directoryPath.toString(), "report-" + shortUUID() + ".html");
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }

            FileOutputStream fos = new FileOutputStream(new File(filePath.toString()));
            byte[] buffer = new byte[4096];
            int length; 
            while((length = in.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            return filePath.toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
