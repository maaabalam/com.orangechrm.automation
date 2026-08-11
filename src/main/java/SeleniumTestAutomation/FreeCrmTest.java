package SeleniumTestAutomation;

import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FreeCrmTest extends PropertiesFileLoad{

	public static void main(String[] args) throws InterruptedException {
		
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\mdaay\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("https://opensource-demo.orangehrmlive.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(6000));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	
	//Login
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	
	//Verify the  homepage after login
	String shomepageTitle =driver.findElement(By.xpath("//div[@class=\"oxd-topbar-header-title\"]//h6")).getText();
	if (shomepageTitle.equals("Dashboard")) {
		System.out.println("Loogin successfully and homepage is open");
	}
	
	
	
	//Close the instance
	driver.quit();
	
	
		
	}
}
