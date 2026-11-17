package com.orangeHRMpage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesFileLoad {

	public static String getPcopertyFile(String property) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\mdaay\\eclipse-workspace\\com.freecrm.com\\utils\\config.properties");
		prop.load(file);
		
		String propertyValue = prop.getProperty(property);
		
		return propertyValue;
	}
	
	
}
