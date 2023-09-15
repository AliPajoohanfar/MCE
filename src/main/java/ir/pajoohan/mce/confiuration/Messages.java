package ir.pajoohan.mce.confiuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Messages {

    public static String get(String index) {

        try (InputStream input = new FileInputStream("./src/main/resources/messages.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty(index);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
