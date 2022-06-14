package gmailRegistration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class KeemGmailRegistration {
	
	   
	   
	   WebDriver driver;

		//
		@BeforeMethod
		public void setUp() {
			System.out.println("Open Chrome Browser");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\p.alakeem.abdulla\\Desktop\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		}
		
		@Test
		public void gmailRegistrationTest() {
			System.out.println("Gmail Register");
		driver.get("https://accounts.google.com/signup");
		driver.findElement(By.id("firstName")).sendKeys("keem");
		driver.findElement(By.id("lastName")).sendKeys("sabile");
		driver.findElement(By.id("username")).sendKeys("keempogi132");
		driver.findElement(By.xpath("//*[@id=\"passwd\"]/div[1]/div/div[1]/input")).sendKeys("123456789");
		driver.findElement(By.xpath("//*[@id=\"confirm-passwd\"]/div[1]/div/div[1]/input")).sendKeys("123456789");
		driver.findElement(By.xpath("//*[@id=\"i2\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"accountDetailsNext\"]/div/button/span")).click();
		}
		
	
		
		@AfterMethod
		public void close() {
			System.out.println("Close Chrome Browser");
		driver.quit();
		}
		
		
}
		 
			
	
	

