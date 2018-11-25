package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_TextBox_TextArea {
	
	WebDriver driver;
	
	private String newName, newDob, newAddress, newCity, newSate, newPin, newPhone, newEmail, newPassword;
	private String  editAddress, editCity, editSate, editPin, editPhone, editEmail, editPassword,customerID;
	
	
	
	By nameByTextbox= By.xpath("//input[@name='name']");
	By dobByTextbox= By.xpath("//input[@name='dob']");
	By addrByTextarea= By.xpath("//textarea[@name='addr']");
	By cityByTextbox= By.xpath("//input[@name='city']");
	By stateByTextbox= By.xpath("//input[@name='state']");
	By pinByTextbox= By.xpath("//input[@name='pinno']");
	By phoneByTextbox= By.xpath("//input[@name='telephoneno']");
	By emailByTextbox= By.xpath("//input[@name='emailid']");
	By passByTextbox= By.xpath("//input[@name='password']");
	By SubmitByButton= By.xpath("//input[@name='sub']");

	@BeforeClass
	public void beforeClass() {
		// Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();

		// Firefox
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		/*   input data*/
		newName = "Automation Test";
		newDob ="2000-10-01";
		newAddress="123 Address";
		newCity="lao Cai";
		newSate="Sa PA";
		newPin="123456";
		newPhone="0324586698";
		newEmail="newtest"+ Commont.randomEmail()+"@gmail.com";
		newPassword="123123";
		
		
		editAddress="234 Edit Address";
		editCity="Vinh";
		editSate="Nghe An";
		editPin="654321";
		editPhone="0357953217";
		editEmail="edittest"+ Commont.randomEmail()+"@gmil.com";
		
	
	
	
	}

	@Test
	public void TC01_NewCustomer() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr161493");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("harErAh");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		/*      New Customer*/
		driver.findElement(nameByTextbox).sendKeys(newName);
		driver.findElement(dobByTextbox).sendKeys(newDob);
		driver.findElement(addrByTextarea).sendKeys(newAddress);
		driver.findElement(cityByTextbox).sendKeys(newCity);
		driver.findElement(stateByTextbox).sendKeys(newSate);
		driver.findElement(pinByTextbox).sendKeys(newPin);
		driver.findElement(phoneByTextbox).sendKeys(newPhone);
		driver.findElement(emailByTextbox).sendKeys(newEmail);
		driver.findElement(passByTextbox).sendKeys(newPassword);
		driver.findElement(SubmitByButton).click();
		
		//get ra customer ID
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID=" +customerID);
		
		//verify input data matching output data after create customer success
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),newName );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),newDob );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),newAddress );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),newCity );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),newSate );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),newPin );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),newPhone );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),newEmail );
	
		
		System.out.println("New"+ driver.findElement(By.xpath(".//td[text()='Customer Name']/following-sibling::td")).getText());
		System.out.println("New"+driver.findElement(By.xpath(".//td[text()='Birthdate']/following-sibling::td")).getText());
		System.out.println("New"+driver.findElement(By.xpath(".//td[text()='Address']/following-sibling::td")).getText());
		System.out.println("New"+driver.findElement(By.xpath(".//td[text()='City']/following-sibling::td")).getText());
		System.out.println("New"+driver.findElement(By.xpath(".//td[text()='State']/following-sibling::td")).getText());
		System.out.println("New"+driver.findElement(By.xpath(".//td[text()='Pin']/following-sibling::td")).getText());
		System.out.println("New"+driver.findElement(By.xpath(".//td[text()='Mobile No.']/following-sibling::td")).getText());
		System.out.println("New"+driver.findElement(By.xpath(".//td[text()='Email']/following-sibling::td")).getText());

		
		
		
		
		
		
		
		
	}
	
	
	@Test
	public void TC02_EditCustomer() {
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);   //send keys vào biến customerID
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		//verify thuoc tinh of customer name va address matching voi input data 
		Assert.assertEquals(driver.findElement(nameByTextbox).getAttribute("value"),newName );
		Assert.assertEquals(driver.findElement(addrByTextarea).getText(), newAddress);
		
		//Edit customer
		
		
		driver.findElement(addrByTextarea).clear();
		driver.findElement(addrByTextarea).sendKeys(editAddress);
		driver.findElement(cityByTextbox).clear();
		driver.findElement(cityByTextbox).sendKeys(editCity);
		driver.findElement(stateByTextbox).clear();
		driver.findElement(stateByTextbox).sendKeys(editSate);
		driver.findElement(pinByTextbox).clear();
		driver.findElement(pinByTextbox).sendKeys(editPin);
		driver.findElement(phoneByTextbox).clear();
		driver.findElement(phoneByTextbox).sendKeys(editPhone);
		driver.findElement(emailByTextbox).clear();
		driver.findElement(emailByTextbox).sendKeys(editEmail);
		driver.findElement(SubmitByButton).click();
		
		//verify input data matching output data after edit customer success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddress );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editCity );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editSate );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),editPin );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editPhone );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail );
	
		//
		
		System.out.println("Edit" + driver.findElement( By.xpath(".//td[text()='Address']/following-sibling::td")).getText());
		System.out.println("Edit" +driver.findElement(By.xpath(".//td[text()='City']/following-sibling::td")).getText());
		System.out.println("Edit" +driver.findElement(By.xpath(".//td[text()='State']/following-sibling::td")).getText());
		System.out.println("Edit" +driver.findElement(By.xpath(".//td[text()='Pin']/following-sibling::td")).getText());
		System.out.println("Edit" +driver.findElement(By.xpath(".//td[text()='Mobile No.']/following-sibling::td")).getText());
		System.out.println("Edit" +driver.findElement(By.xpath(".//td[text()='Email']/following-sibling::td")).getText());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
