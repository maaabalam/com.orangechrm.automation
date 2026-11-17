package com.orangeHRMpage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage{
	private final WebDriver driver;
	
	@FindBy(xpath="//div[@class='oxd-topbar-header-title']//h6")
	private WebElement sTitle;
	
	@FindBy(xpath="//ul[@class= 'oxd-main-menu']//span")
	List<WebElement> sLeftHandMenus;
	
	@FindBy(xpath="//span[text()= 'PIM']/parent::a")
	WebElement sPIMMenu;
	
	@FindBy(linkText="Add Employee")
	WebElement addemployee;
	
	@FindBy(name="firstName")
	WebElement fName;
	
	@FindBy(name="lastName")
	WebElement lName;
	
	@FindBy(xpath="(//div[@class='orangehrm-employee-form']//input[starts-with(@class,'oxd-input oxd-input')])[4]")
	WebElement emplId;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isDashboardDisplayed() {
		String title = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOf(sTitle))
				.getText();
		return title.equals("Dashboard");
	}
	
	public boolean VerifyLeftHandMenuPresent() {
		List<String> sExpectedMenus = List.of("Admin", "PIM", "Leave", "Time", "Recruitment", "My Info", "Performance", "Dashboard", "Directory", "Maintenance", "Claim", "Buzz");
		List<String> sActualLeftHandMenusn = new ArrayList<>();
		
		for (WebElement sLHM: sLeftHandMenus) {
			String menuText = sLHM.getText();
			if (menuText != null && !menuText.trim().isEmpty()) {
				sActualLeftHandMenusn.add(menuText.trim());
			}
		}
		return sExpectedMenus.equals(sActualLeftHandMenusn);
	}
	
	public void VerifyNewEmployeeCanBeAdded(String fname, String lname, String emplid) throws InterruptedException {
		sPIMMenu.click();
		addemployee.click();
		fName.sendKeys(fname);
		lName.sendKeys(lname);
		emplId.sendKeys(emplid);
		submitBtn.click();
		Thread.sleep(10000);
	}

}