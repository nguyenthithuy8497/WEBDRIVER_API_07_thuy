package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic02_Xpath_css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();

		// Firefox
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/manager/CustomerRegMsg.php?cid=66044");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		
		
		/*input id="pass" 
		class="input-text required-entry validate-password" 
				type="password"
				title="Password" 
				name="login[password]"/>*/
		/*
		 *id
		 *class
		 *name
		 *linktext
		 *partial linktext
		 *tagname
		 *css : khong cho phep check containt,text. chi cho 
		 *xpath
		 */
		/*
		driver.findElement(By.className("input-text required-entry validate-password")).click();;
		
		driver.findElement(By.id("pass")).click();
		
		driver.findElement(By.name("login[password]")).click();
		
		//tu tagname lay ra attribute. tuy nhien truong hop nay it dung
		//get ra tat ca cac button trong page
		driver.findElement(By.tagName("button")).click();
		
		driver.findElement(By.linkText("Site Map")).click();
		
		driver.findElement(By.partialLinkText("Site")).click();
		
		driver.findElement(By.partialLinkText("Map")).click();

		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']"));
		
		driver.findElement(By.xpath("//input[@name='login[password]']"));
*/
		
		//open my account 
		String customorID= driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID" +customorID);
		Assert.assertEquals(customorID,"66044");
		
		
		
		
		
		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
