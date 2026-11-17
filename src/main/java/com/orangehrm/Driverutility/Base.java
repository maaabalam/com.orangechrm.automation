package com.orangehrm.Driverutility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.orangehrm.utilities.PropertiesFileLoad;

public class Base extends DriverUtility {
	public static WebDriver driver;
	public void initialize() {
		
		try {
			String sBrowser = PropertiesFileLoad.getProperty("browser");
			String url = PropertiesFileLoad.getProperty("url");
			createDriver(sBrowser);
			driver = getDriver();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
