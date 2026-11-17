package com.orangehrm.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileLoad {

	public static String getProperty(String property) throws IOException {
		Properties properties = new Properties();
		try (InputStream input = PropertiesFileLoad.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				throw new IOException("Could not find config.properties on the test classpath");
			}
			properties.load(input);
		}

		String value = System.getProperty(property);
		if (value == null || value.isBlank()) {
			value = properties.getProperty(property);
		}
		if (value == null || value.isBlank()) {
			throw new IOException("Missing configuration value: " + property);
		}
		return value.trim();
	}
}
