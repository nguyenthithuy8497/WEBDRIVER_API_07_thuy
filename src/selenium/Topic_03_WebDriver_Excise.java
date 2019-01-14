package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic03_WebDriver_Excise {
	
	WebDriver driver;
	//khai bao de dung di dung lai nhieu lan
	By emailByTextBox=By.xpath("//input[@id='mail']");
	By ageUnder18ByRadio=By.xpath("//input[@id='under_18']");
	By educationByTextArea=By.xpath("//textarea[@id='edu']");
	By jobRole01ByDropDown=By.xpath("//select[@id='job1']");
	By interestsByCheckbox=By.xpath("//input[@id='development']");
	By slider01ByText=By.xpath("//input[@id='slider-1']");
	By butonIsEnableByclick=By.xpath("//button[@id='button-enabled']");
	
	By passwordByTextbox= By.xpath("//input[@id='password']");
	By ageIsDisableByRadio=By.xpath("//input[@id='radio-disabled']");
	By biographyByTextArea=By.xpath("//textarea[@id='bio']");
	By jobRole02ByDropDown=By.xpath("//select[@id='job2']");
	By interestsIsDisableByCheckbox=By.xpath("//input[@id='check-disbaled']");
	By slider02ByText=By.xpath("//input[@id='slider-2']");
	By buttonIsDisableByButton=By.xpath("//button[@id='button-disabled']");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_IsDisplayed() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		/*if(driver.findElement(emailByTextBox).isDisplayed()) {
			driver.findElement(emailByTextBox).sendKeys("Automation Testing");
			
		} else
		{
			System.out.println("Element["+ emailByTextBox +"]is displayed");
		}
		
		
		if(driver.findElement(educationByTextArea).isDisplayed()) {
			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
			
		} else
		{
			System.out.println("Element["+ educationByTextArea +"]is displayed");
		}
		
		
		if(driver.findElement(ageUnder18ByRadio).isDisplayed()) {
			driver.findElement(ageUnder18ByRadio).click();
			
		} else
		{
			System.out.println("Element["+ ageUnder18ByRadio +"]is displayed");
		}*/
		
		
		if(isControlDisplay(emailByTextBox)) {
			driver.findElement(emailByTextBox).sendKeys("Automation Testing");
		}
		if(isControlDisplay(educationByTextArea)) {
			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		}
		if(isControlDisplay(ageUnder18ByRadio)) {
			driver.findElement(ageUnder18ByRadio).click();
		}	
	}
	
	@Test
	public void TC_02_IsEnable() {
		//Step 01 - Truy cập vào trang: 
		//https://daominhdam.github.io/basic-form/index.html
		
		//Step 02 - Kiểm tra các phần tử sau enable trên trang:
		//Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/ Slider 01/ Button is enabled
		//Nếu có in ra Element is enabled
		//enable
		Assert.assertTrue(isControlEnable(emailByTextBox));
		Assert.assertTrue(isControlEnable(ageUnder18ByRadio));
		Assert.assertTrue(isControlEnable(educationByTextArea));
		Assert.assertTrue(isControlEnable(jobRole01ByDropDown));
		Assert.assertTrue(isControlEnable(interestsByCheckbox));
		Assert.assertTrue(isControlEnable(slider01ByText));
		Assert.assertTrue(isControlEnable(butonIsEnableByclick));
		
		//Step 03 - Kiểm tra các phần tử sau disable trên trang:
		//Password / Age (Radiobutton is disabled)/ Biography/ Job Role 02/ Interests (Checkbox is disabled)/ 
		//Slider 02/ Button is disabled
		// Nếu có in ra  Element is disabled
		//disable
		Assert.assertFalse(isControlEnable(passwordByTextbox));
		Assert.assertFalse(isControlEnable(ageIsDisableByRadio));
		Assert.assertFalse(isControlEnable(biographyByTextArea));
		Assert.assertFalse(isControlEnable(jobRole02ByDropDown));
		Assert.assertFalse(isControlEnable(interestsIsDisableByCheckbox));
		Assert.assertFalse(isControlEnable(slider02ByText));
		Assert.assertFalse(isControlEnable(buttonIsDisableByButton));
		
	}

	@Test
	public void TC_03_IsSelected() {
	//Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	
		
		//Step 02 - Click chọn Age (Under 18)/ Interests (Development)
		
			driver.findElement(ageUnder18ByRadio).click();
			driver.findElement(interestsByCheckbox).click();
			
			Assert.assertTrue(isControlSelected(ageUnder18ByRadio));
			Assert.assertTrue(isControlSelected(interestsByCheckbox));
			
			//click để bỏ chọn
			driver.findElement(interestsByCheckbox).click();
			Assert.assertFalse(isControlSelected(interestsByCheckbox));
	//Step 03 - Kiểm tra các phần tử tại Step 02 đã được chọn	
		//Step 04 - Nếu chưa được chọn thì cho phép chọn lại 1 lần nữa
		if( !isControlSelected(interestsByCheckbox)) {
			driver.findElement(interestsByCheckbox).click();
			Assert.assertTrue(isControlSelected(interestsByCheckbox));
		}
		
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isControlDisplay(By by) {
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			System.out.println("Element["+ by + "]is displayed");
			return true;
		} else {
			System.out.println("Element["+ by +"]is not displayed");
			return false;
		}
		
	}
	
	public boolean isControlSelected(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element["+ by + "]is selected");
			return true;
		} else {
			System.out.println("Element["+ by +"]is not selected");
			return false;
		}
		
	}
	public boolean isControlEnable(By by) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element["+ by + "]is enable");
			return true;
		} else {
			System.out.println("Element["+ by +"]is not enable");
			return false;
		}
		
	}
	

}
