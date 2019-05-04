import java.io.FileInputStream;
import java.util.Properties;

//class for loading rabbitmq configs from file
public final class BrokerProperty {

    private static Properties rabbitmqProperties = new Properties();

    static {
        try {
            FileInputStream propertyFileStream = new FileInputStream("src/main/resources/config.properties");
            rabbitmqProperties.load(propertyFileStream); //load all configs from file to object
            propertyFileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //return string with parameter by key, or return default parameter value if there is no key in file
    public static String load(String key, String defaultValue) {
        return rabbitmqProperties.getProperty(key, defaultValue);
    }
}


