import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Singleton {
    private static Singleton instance;
    private final Map<String, String> propertiesMap;

    private Singleton() {
        propertiesMap = new HashMap<>();
        File file = new File("src/test.txt");
        try (FileInputStream fis = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(fis);
            for (String key : properties.stringPropertyNames()) {
                propertiesMap.put(key, properties.getProperty(key));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getProperty(String key) {
        return propertiesMap.get(key);
    }
}