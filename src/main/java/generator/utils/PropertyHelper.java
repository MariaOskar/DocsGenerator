package generator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

    public static String getProperty(String filename, String property) {
        InputStream is = DataGenerator.class.getClassLoader().getResourceAsStream(filename);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(property, "");
    }
}
