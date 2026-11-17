package com.orangehrm.Driverutility;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility {

	private static WebDriver driver;
	
	public static void createDriver(String browser) {
		if (browser == null || browser.isBlank()) {
			throw new IllegalArgumentException("Browser must be configured");
		}

		switch (browser.trim().toLowerCase(Locale.ROOT)) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--lang=en-US");
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
}
