package com.freecrm.com;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHRMpage.HomePage;
import com.orangeHRMpage.LoginPage;

public class orangeTest {
	HomePage homePage;
	
	@Test(priority=1)
	public void validLoginOpensDashboard() {
		homePage = new LoginPage().login();
		Assert.assertTrue(homePage.isDashboardDisplayed(), "Successful login should open the Dashboard");
	}
	
	@Test(priority=2)
	public void verifyTheHomePageMenuItems() {
		Assert.assertTrue(homePage.VerifyLeftHandMenuPresent(), "The left-hand dashboard menu items do not match the expected OrangeHRM list");
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {
			{"Steve","Smith","21"}, {"Joe","Root","25"}, {"Virat","Kohli","18"}, {"MS","Dhoni","07"}
		};
	}
	
	@Test(priority=3, dataProvider = "getData")
	public void VerifyNewEmployeeCanBeAdded(String fname, String lname, String emplid) throws InterruptedException {
		homePage.VerifyNewEmployeeCanBeAdded(fname, lname, emplid);
	}

}
