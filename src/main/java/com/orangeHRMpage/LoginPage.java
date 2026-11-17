package com.orangeHRMpage;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.Driverutility.Base;
import com.orangehrm.utilities.PropertiesFileLoad;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Base{
	public String username;
	public String passwordValue;
	@FindBy(xpath="//input[@name='username']")
	private WebElement uname;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submitButton;
	
	public LoginPage(){
		if (driver == null) {
			new Base().initialize();
		}
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login() {
		
		try {
			username = PropertiesFileLoad.getProperty("username");;
			passwordValue = PropertiesFileLoad.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(uname));
		uname.sendKeys(username);
		password.sendKeys(passwordValue);
		submitButton.click();
		return new HomePage(driver);
	}
}