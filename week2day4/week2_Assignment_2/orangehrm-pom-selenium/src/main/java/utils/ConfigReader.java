package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties prop = new Properties();

    public ConfigReader(String filePath) {
        try (InputStream input = new FileInputStream(filePath)) {
            prop.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load config: " + e.getMessage(), e);
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
}
