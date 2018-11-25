package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Button {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		// Firefox
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	public void TC_01_HTML_button() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//verify page URL
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		//click create an account bang js
		WebElement createAnAccount =driver.findElement(By.xpath("//a[@title='Create an Account']"));
		Commont.clickElementByJavascript(driver, createAnAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
	}

	public void TC_02_Customer_Checkbox() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement dualzoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));
		//click by javascrip
		Commont.clickElementByJavascript(driver, dualzoneCheckbox);
		Thread.sleep(3000);
		//verify dualzone selected
		Assert.assertTrue(dualzoneCheckbox.isSelected());
		//uncheck=>verify unselected
		Commont.clickElementByJavascript(driver, dualzoneCheckbox);
		//verify
		Assert.assertFalse(dualzoneCheckbox.isSelected());
	
	}
	
	
	public void TC_03_Customer_RadioButton() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		WebElement PetroRadioButton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input"));
		WebElement DiezenRadioButton= driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input"));
		//click by javascript
		Commont.clickElementByJavascript(driver, PetroRadioButton);
		//verify
		Assert.assertTrue(PetroRadioButton.isSelected());
		//Radio button k co su kien uncheck
		//chi unckeck khi chon button khac
		Commont.clickElementByJavascript(driver, DiezenRadioButton);
		Assert.assertTrue(DiezenRadioButton.isSelected());
		//khi diezen dc chon thanh cong thi petro bi un check
		Assert.assertFalse(PetroRadioButton.isSelected());

	}

	public void TC_04_Customer_JSAlert() throws Exception {
		By resultMessage = By.xpath("//p[@id='result']");
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Thread.sleep(1000);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Thread.sleep(1000);
		
		//verify c1
		Assert.assertTrue(driver.findElement(resultMessage).getText().equals("You clicked an alert successfully"));
		//verify c2
		//Assert.assertEquals(driver.findElement(resultMessage).getText(), "You clicked an alert successfully");
		Thread.sleep(1000);
	}
	
	public void TC_05_Customer_JSConfirm() throws Exception {
		By resultMessage = By.xpath("//p[@id='result']");
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Thread.sleep(1000);
		
		Alert confirm = driver.switchTo().alert();
		Assert.assertEquals(confirm.getText(), "I am a JS Confirm");
		confirm.dismiss();
		Thread.sleep(1000);
		
		//verify
		
		Assert.assertEquals(driver.findElement(resultMessage).getText(), "You clicked: Cancel");
		Thread.sleep(1000);

	}
	
	public void TC_06_Customer_JSPrompt() throws Exception {
		By resultMessage = By.xpath("//p[@id='result']");
		String name="Thuy";
		
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Thread.sleep(1000);
		
		//alert
		Alert prompt= driver.switchTo().alert();
		Assert.assertEquals(prompt.getText(), "I am a JS prompt");
		prompt.sendKeys(name);
		prompt.accept();
		
		Assert.assertEquals(driver.findElement(resultMessage).getText(), "You entered: " +name);
		Thread.sleep(1000);
	
	}
	
	@Test
	public void TC_07_Customer_Athenication() throws Exception {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Thread.sleep(1000);		
		//http://username:password@domain...
		//tu dong dang nhap
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
		
		
	}
		
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
