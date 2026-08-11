package com.freecrm.com;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class orangeHCMTest {
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mdaay\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(6000));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
	}
	
	@Test(priority = 0)
	public void VerifyLoginWithValidCredentials() {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test(priority = 1)
	public void VerifyHomePageISOpeningAfterLogin() {
		String shomepageTitle = driver.findElement(By.xpath("//div[@class=\"oxd-topbar-header-title\"]//h6")).getText();
		Assert.assertEquals(shomepageTitle, "Dashboard");
	}
	
	@Test(priority = 2)
	public void VerifyLeftHandMenuPresent() {
		List<String> sExpectedMenus = List.of("Admin", "PIM", "Leave", "Time", "Recruitment", "My Info", "Performance", "Dashboard", "Directory", "Maintenance", "Claim", "Buzz");
		List<String> sActualLeftHandMenusn = new ArrayList<String>();
		List<WebElement> sLeftHandMenus = driver.findElements(By.xpath("//ul[@class= 'oxd-main-menu']//span"));
		for (WebElement sLHM: sLeftHandMenus) {
			sActualLeftHandMenusn.add(sLHM.getText());
		}
		Assert.assertEquals(sExpectedMenus, sActualLeftHandMenusn);
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {
			{"Steve","Smith","21"}, {"Joe","Root","25"}, {"Virat","Kohli","18"}, {"MS","Dhoni","07"}
		};
	}
	
	@Test(priority = 4, dataProvider = "getData")
	public void VerifyNewEmployeeCanBeAdded(String fname, String lname, String emplid) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()= 'PIM']/parent::a")).click();
		driver.findElement(By.linkText("Add Employee")).click();
		driver.findElement(By.name("firstName")).sendKeys(fname);
		driver.findElement(By.name("lastName")).sendKeys(lname);
		driver.findElement(By.xpath("(//div[@class='orangehrm-employee-form']//input[starts-with(@class,'oxd-input oxd-input')])[4]")).sendKeys(emplid);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(10000);
	}
	
	@Test(priority = 5)
	public void VerifyNewAddedEmployee() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()= 'PIM']/parent::a")).click();
		driver.findElement(By.linkText("Employee List")).click();
		driver.findElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//div[@class='oxd-autocomplete-wrapper']//input[@placeholder='Type for hints...']")).sendKeys("Vir");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-option']/span)[1]"))));
		driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-option']/span)[1]")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		List<WebElement> sEmployeesDetais = driver.findElements(By.xpath("//div[@class='oxd-table-card']"));
		
		boolean bFound = false;
		for(int i = 1; i<= sEmployeesDetais.size(); i++) {
			for(int j = 1; j<=9; j++ ){
				Thread.sleep(5000);
				String sEmployeeName = driver.findElement(By.xpath("(//div[@class='oxd-table-card']/div)["+i+"]/div[3]/div")).getText();
				if (sEmployeeName.equals("Virat")){
					bFound = true;
					break;
				}
			
			}
			break;
		}
		Assert.assertEquals(bFound, true);
	}

	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
