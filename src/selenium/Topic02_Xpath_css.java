package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_Xpath_css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();

		// Firefox
		//khoi tao firefox browes
		driver = new FirefoxDriver();
		
		//open app
		//driver.get("http://live.guru99.com/");
		//phong to trinh duyet
		driver.manage().window().maximize();
		//wait fine Element duoc tim trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_CheckUrlAndTitle1() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Kiểm tra title của page là: "Home page"
		String homePageTitle=driver.getTitle();
		Assert.assertEquals(homePageTitle,"Home page");
		Assert.assertTrue(homePageTitle.equals("Home page"));
		
		//Step 03 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Step 04 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		//Step 05 - Back lại trang đăng nhập
		driver.navigate().back();
		
		//Step 06 - Kiểm tra url của page đăng nhập là: http://live.guru99.com/index.php/customer/account/login/
		String loginUrl=driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
		
		//Step 07 - Forward tới trang tạo tài khoản
		driver.navigate().forward();
		
		//Step 08 - Kiểm tra url của page tạo tài khoản là: http://live.guru99.com/index.php/customer/account/create/
		String createAccount= driver.getCurrentUrl();
		Assert.assertEquals(createAccount,  "http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_02_EmailAndPasswordEmpty() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Step 03 - Để trống Username/ Password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		
		//Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
		String emailErrorMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailErrorMessage, "This is a required field.");
		
		String passwordErrorMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passwordErrorMessage, "This is a required field.");
	}
	
	@Test
	public void TC_03_EmailIncorrectAndPasswordValid() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Step 03 - Nhập email invalid: 123434234@12312.123123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		//step_05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
		String emailErrorMessager=driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMessager, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_04_EmailCorrectAndPasswordLessThan6Chars() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		//Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
		String passwordLessThan6Char =driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordLessThan6Char, "Please enter 6 or more characters without leading or trailing spaces.");
				
		
	}
	
	@Test
	public void TC_05_EmailCorrectAndPasswordIncorrect() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123123123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		//Step 04 - Click Login button
		driver.findElement(By.id("send2")).click();
		//Step 05 - Verify error message xuất hiện: Invalid login or password.
		String invalidLoginOrPassword = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(invalidLoginOrPassword, "Invalid login or password.");
	}
	
	@Test
	public void TC_06_RegisterToSystem() {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//span[text()=\"Create an Account\"]")).click();
		
		
		//Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
		// Tạo random cho dữ liệu tại field Email Address
		
		String firstname="Selenium", lastname="Online 07",
		email="seleniumonline" +randomEmail() +"@gmail.com",
		password="123123123";
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.className("validate-password")).sendKeys(password);
		driver.findElement(By.className("validate-cpassword")).sendKeys(password);
		
		//Step 05 - Click REGISTER button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		//Step 06 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()=\"Thank you for registering with Main Website Store.\"]")).isDisplayed());
		
		//Step 07 - Logout khỏi hệ thống
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		//Step 08 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		
		
	}
	public int randomEmail() {
		Random random= new Random();
		int number =random.nextInt(99999);
		System.out.println("Random number= " +number);
		return number;
		
	}

}
