package projectEARF;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;

public class assertPDF { 
	
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.out.println("Open Chrome Browser");
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\p.alakeem.abdulla\\Desktop\\chromedriver_win32\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://localhost:8080/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	}

	@Test (priority = 7)
	public void EARFWebPage() throws InterruptedException, IOException, FindFailed {
		System.out.println("Open Web Page");	
		//String path = ".//screenshot/EARF";
	
	
	/*
	 * //HTML Report test = extent.createTest("EARF Web Page Output"); //Test Case
	 * Title test.log(Status.INFO, "Start of test case,  Open chrome browser");
	 * //Start of Extent Report
	 */	  
	
	//Before Method Actions 
	Thread.sleep(1000); 
	//test.pass("User goes to EARF web app ");
	  
	//Employee Information
	JavascriptExecutor js = (JavascriptExecutor) driver;
	driver.findElement(By.id("employeeNumber")).sendKeys("11590");
	driver.findElement(By.id("yes")).click();
    driver.findElement(By.id("converge")).click(); 
    Thread.sleep(1000);
	driver.findElement(By.id("lastName")).sendKeys("Abdulla");
	driver.findElement(By.id("firstName")).sendKeys("Al-Akeem");
	driver.findElement(By.id("middleName")).sendKeys("Sabile");
	Thread.sleep(1000);
	driver.findElement(By.id("emailInput")).sendKeys("p.asabdulla@convergeict.com");
	driver.findElement(By.id("departmentInput")).sendKeys("IT");
	
	/*
	 * //Take Screenshot File scrnshot =
	 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 * FileUtils.copyFile(scrnshot, new File(path + "employeeInfo1.png"));
	 * //test.pass("[Verify] User input information");
	 */	
	Thread.sleep(1000);
	js.executeScript("window.scrollTo(0, 450)");
	Thread.sleep(1000);
	driver.findElement(By.id("contactNumberInput")).sendKeys("09876543210");
	Thread.sleep(1000); driver.findElement(By.id("jobtitleInput")).sendKeys("ITAP");
	driver.findElement(By.xpath("//*[@id=\"loc\"]")).click();
	Thread.sleep(1000);
	
	//Application
	js.executeScript("window.scrollTo(0, 1800)");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id=\"bss\"]/td[7]/input")).click(); // BSS Admin
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"u2000\"]/td[6]/input")).click();//U2000 Import
	Thread.sleep(1000);
	
	WebElement lastname = driver.findElement(By.id("lastName"));
	WebElement firstname = driver.findElement(By.id("firstName"));
	WebElement middlename = driver.findElement(By.id("middleName"));
	
	String LNval = lastname.getAttribute("value");
	String FNval = firstname.getAttribute("value");
	String MNval = middlename.getAttribute("value");
	
	//scroll to the bottom of the web app
	js.executeScript("window.scrollTo(0, 2800)");
	Thread.sleep(1000);
	
	//Add Tools+
	driver.findElement(By.id("addTools")).click();//Add Tools+
	js.executeScript("window.scrollTo(0, 3000)");
	Thread.sleep(1000);
	driver.findElement(By.id("toolsInputElement")).sendKeys("Eclipse");
	driver.findElement(By.id("toolsInputElement1")).sendKeys("Java");
	driver.findElement(By.id("toolsInputElement2")).sendKeys("Ubuntu");
	Thread.sleep(2000);
	
	//Modify PDF 
	driver.findElement(By.id("copyBtn")).click();
	Thread.sleep(2000); 
	driver.switchTo().alert().accept();
	Thread.sleep(5000);	 
	
	//Verify PDF Output - Assert
	File file = new File("C://Users//p.alakeem.abdulla//Downloads//" +"EARF_" + LNval +"," + FNval + " " + MNval + ".pdf");	
	FileInputStream fis = new FileInputStream(file);
	PDFParser parser = new PDFParser(fis);
	parser.parse();
	
	COSDocument cosDocument = parser.getDocument();
	PDDocument pdDocument = new PDDocument(cosDocument);
	PDFTextStripper stripper = new PDFTextStripper();
	String data = stripper.getText(pdDocument);
	
	cosDocument.close();
	pdDocument.close();
	
    //PDF Assert Output   
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("employeeNumber")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("emailInput")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("departmentInput")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("contactNumberInput")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("jobtitleInput")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.xpath("//*[@id=\"loc\"]")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("toolsInputElement")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("toolsInputElement1")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(
			driver.findElement(By.id("toolsInputElement2")).getAttribute("value")));
	AssertJUnit.assertTrue(data.contains(LNval));
	AssertJUnit.assertTrue(data.contains(FNval));
	AssertJUnit.assertTrue(data.contains(MNval));
	
	System.out.println(data);
	
	/*
	 * File file = new File("src/main/resources/images/Capture.PNG"); Screen s = new
	 * Screen(); Pattern clickMap = new Pattern(file.getAbsolutePath()); Match match
	 * = s.exists(clickMap); Thread.sleep(2000); s.doubleClick(match);
	 */
	 
	}
	
	@AfterMethod
	public void teardown() {
		System.out.println("Close Browser");
	driver.close();
	}
}
