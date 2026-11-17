package com.freecrm.com;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class orangeHCMTest extends BaseTest {
	
	
	
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {
			{"Steve","Smith","21"}, {"Joe","Root","25"}, {"Virat","Kohli","18"}, {"MS","Dhoni","07"}
		};
	}
	
	@Test(dependsOnMethods = "VerifyLeftHandMenuPresent", dataProvider = "getData")
	public void VerifyNewEmployeeCanBeAdded(String fname, String lname, String emplid) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()= 'PIM']/parent::a")).click();
		driver.findElement(By.linkText("Add Employee")).click();
		driver.findElement(By.name("firstName")).sendKeys(fname);
		driver.findElement(By.name("lastName")).sendKeys(lname);
		driver.findElement(By.xpath("(//div[@class='orangehrm-employee-form']//input[starts-with(@class,'oxd-input oxd-input')])[4]")).sendKeys(emplid);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(10000);
	}
	
	@Test(dependsOnMethods = "VerifyNewEmployeeCanBeAdded")
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
	
	@Test(dependsOnMethods = "VerifyNewAddedEmployee")
	public void VerifyDirectoryPageIsOPening() {
		driver.findElement(By.xpath("//span[text()= 'Directory']/parent::a")).click();
		String sDirectoery = driver.findElement(By.xpath("//h6[text()='Directory']")).getText();
		Assert.assertEquals(sDirectoery, "Directory");
	}
	
	@Test(dependsOnMethods = "VerifyDirectoryPageIsOPening")
	public void VerifyAddedEmployeeInDirectory() {
		driver.findElement(By.xpath("//span[text()= 'Directory']/parent::a")).click();
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h5[text()='Directory']"))));
		
		driver.findElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//div[@class='oxd-autocomplete-wrapper']//input[@placeholder='Type for hints...']")).sendKeys("Vir");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-option']/span)[1]"))));
		driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-option']/span)[1]")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String sEmployeeFound = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
		boolean bFound = false;
		if (sEmployeeFound.contains("Records Found")) {
			bFound = true;
		}
		Assert.assertEquals(bFound, true);
	}
	
	@Test(dependsOnMethods = "VerifyAddedEmployeeInDirectory")
	public void VerifyAdminPage() {
		driver.findElement(By.xpath("//span[text()= 'Admin']/parent::a")).click();
		String sDirectoery = driver.findElement(By.xpath("//h6[text()='Admin']")).getText();
		Assert.assertEquals(sDirectoery, "Admin");
	}

	@Test(dependsOnMethods = "VerifyAdminPage")
	public void VerifyAdminInAdminPage() {
		driver.findElement(By.xpath("//span[text()= 'Admin']/parent::a")).click();
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h5[text()='System Users']"))));
		
		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys("admin");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String sEmployeeFound = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
		boolean bFound = false;
		if (sEmployeeFound.contains("Records Found")) {
			bFound = true;
		}
		Assert.assertEquals(bFound, true);
	}
	
}
