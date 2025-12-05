package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;

public class TestDataReader {
    private JsonObject json;

    public TestDataReader(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            json = new Gson().fromJson(reader, JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read testdata: " + e.getMessage(), e);
        }
    }

    public String get(String... path) {
        JsonObject node = json;
        for (int i = 0; i < path.length - 1; i++) {
            node = node.getAsJsonObject(path[i]);
        }
        return node.get(path[path.length - 1]).getAsString();
    }
}
