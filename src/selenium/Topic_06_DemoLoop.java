package selenium;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.SysexMessage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_DemoLoop {
	
	WebDriver driver;
	private  String firstname, lastname, email, password, randomNumber;
	private String sourceFolder= System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 1)
	public void TC_01_RegisterToSystem() throws IOException {
		randomNumber= Commont.randomEmail() + " ";
		firstname="Selenium"+ Commont.randomEmail();
		lastname="Online 07";
		email="seleniumonline" +Commont.randomEmail() +"@gmail.com";
		password="123123123";
		
		driver.get("http://live.guru99.com/");	
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()=\"Create an Account\"]")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.className("validate-password")).sendKeys(password);
		driver.findElement(By.className("validate-cpassword")).sendKeys(password);
		
	//capture screenshot
		takeSnapShot(driver, sourceFolder+"\\screenshot"+getTimeStampValue() + ".png");
		
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()=\"Thank you for registering with Main Website Store.\"]")).isDisplayed());

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}
	
	//ham chup man hinh 
	public static void takeSnapShot(WebDriver driver, String fileName) {
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public  void captureScreenShot(String obj) throws IOException {
	    File screenshotFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(screenshotFile,new File("Screenshots\\"+obj+""+getTimeStampValue()+".png"));
	}
		//ngay thang chup man hinh
	public  String getTimeStampValue()throws IOException{
	    Calendar cal = Calendar.getInstance();       
	    Date time=cal.getTime();
	    String timestamp=time.toString();
	    System.out.println(timestamp);
	    String systime=timestamp.replace(":", "-");
	    System.out.println(systime);
	    return systime;
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
