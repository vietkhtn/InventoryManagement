package inventory.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	private Properties properties = null;
	private static ConfigLoader instance = null;
	
	String proFilenName = "config.properties";
	
	private ConfigLoader() {
		// Reading resources of file config.properties
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(proFilenName);
		if (inputStream != null) {
			properties = new Properties();
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// Use Singleton to create ConfigLoader once during thread on system
	public static ConfigLoader getInstance() {
		if (instance == null) {
			instance = new ConfigLoader();
		}
		return instance;
	}
	
	// Get value of config.properties file
	public String getValue(String key) {
		if(properties.containsKey(key)) {
			return properties.getProperty(key);
		}
		return null;
	}
}
