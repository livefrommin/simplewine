package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    protected static FileInputStream fileInputStream;
    protected static FileOutputStream fileOutputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {return PROPERTIES.getProperty(key);}
    public static Properties getProperties() {return PROPERTIES;}
    public static void setProperty(String key, String value) {
        try {
            fileOutputStream = new FileOutputStream("src/main/resources/config.properties");
            PROPERTIES.setProperty(key,value);
            PROPERTIES.store(fileOutputStream,null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
